package firesim.firesim

import freechips.rocketchip.config.Config
import freechips.rocketchip.subsystem._
import boom.system.BoomTilesKey
import boom.common.WithNPerfCounters
import midas.models._
import testchipip.WithBlockDevice

// Custom SimConfigs
class With8MSHRs extends Config((site, here, up) => {
  case LlcKey => up(LlcKey, site).map(_.copy(mshrs = WRange(1, 8)))
})
class With20MSHRs extends Config((site, here, up) => {
  case LlcKey => up(LlcKey, site).map(_.copy(mshrs = WRange(1, 20)))
})

class With1RankDRAM extends Config((site, here, up) => {
  case DramOrganizationKey => up(DramOrganizationKey, site).copy(maxRanks = 1)
})

// Custom TargetConfigs
class With6PerfCounters extends WithNPerfCounters(6)

class With5IdBits extends Config((site, here, up) => {
  case ExtMem => up(ExtMem, site).map(_.copy(idBits = 5))
})

class With16kL1Booms extends Config((site, here, up) => {
  case BoomTilesKey => up(BoomTilesKey, site) map { r =>r.copy(
    dcache = Some(r.dcache.get.copy(nWays=4)),
    icache = Some(r.icache.get.copy(nWays=4))
  )}
})

class With8Trackers extends WithNTrackersPerBank(8)
class With16Trackers extends WithNTrackersPerBank(16)
class With20Trackers extends WithNTrackersPerBank(20)

class With2GHzTarget extends WithPeripheryBusFrequency(BigInt(2130000000L))

class FireBoomDefaultConfig extends Config(
    new WithBootROM ++
    new WithPeripheryBusFrequency(BigInt(3200000000L)) ++
    new WithExtMemSize(0x400000000L) ++ // 16GB
    new WithoutTLMonitors ++
    new WithUARTKey ++
    new WithNICKey ++
    new WithBlockDevice ++
    new WithBoomL2TLBs(1024) ++
    new WithBoomSynthAssertExcludes ++ // Will do nothing unless assertion synth is enabled
    new boom.system.BoomConfig)

class FireBoomDefaultDualCoreConfig extends Config(
  new WithNDuplicatedBoomCores(2) ++
  new FireBoomDefaultConfig)
