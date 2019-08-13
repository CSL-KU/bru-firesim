package firesim.firesim

import chisel3._
import freechips.rocketchip.subsystem._
import freechips.rocketchip.tilelink._
import freechips.rocketchip.devices.tilelink._
import freechips.rocketchip.config.Parameters
import freechips.rocketchip.diplomacy.LazyModule
import boom.system.{BoomSubsystem, BoomSubsystemModule}
import testchipip._
import sifive.blocks.devices.uart._

class FireBoomCustom(implicit p: Parameters) extends BoomSubsystem
  with CanHaveMisalignedMasterAXI4MemPort
  with HasPeripheryBootROM
  with HasNoDebug
  with HasPeripherySerial
  with HasPeripheryUART
  with HasPeripheryBlockDevice
{
  val hasTraces = boomTiles.map(_.boomParams.trace).reduce(_ || _)
  require(hasTraces == false)

  override lazy val module = new FireBoomCustomModuleImp(this)

  // Error device used for testing and to NACK invalid front port transactions
  val error = LazyModule(new TLError(p(ErrorDeviceKey), sbus.beatBytes))
  // always buffer the error device because no one cares about its latency
  sbus.coupleTo("slave_named_error"){ error.node := TLBuffer() := _ }
}

class FireBoomCustomModuleImp[+L <: FireBoomCustom](l: L) extends BoomSubsystemModule(l)
  with HasRTCModuleImp
  with CanHaveMisalignedMasterAXI4MemPortModuleImp
  with HasPeripheryBootROMModuleImp
  with HasNoDebugModuleImp
  with HasPeripherySerialModuleImp
  with HasPeripheryUARTModuleImp
  with HasPeripheryBlockDeviceModuleImp
{
  tile_inputs.foreach{ case(tile_input) =>
    tile_input.perf.blkdev_get := RegNext(outer.controller.module.io.perf.tl_get)
    tile_input.perf.blkdev_put := RegNext(outer.controller.module.io.perf.tl_put)
  }
}