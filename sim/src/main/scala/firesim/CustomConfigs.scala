package firesim.firesim

import freechips.rocketchip.config.Config
import freechips.rocketchip.subsystem._
import boom.system.BoomTilesKey
import midas.models._

// Custom SimConfigs
class With20MSHRs extends Config((site, here, up) => {
  case LlcKey => up(LlcKey, site).map(_.copy(mshrs = WRange(1, 20)))
})

// Custom TargetConfigs
class With5IdBits extends Config((site, here, up) => {
  case ExtMem => up(ExtMem, site).map(_.copy(idBits = 5))
})

class With16kL1Booms extends Config((site, here, up) => {
  case BoomTilesKey => up(BoomTilesKey, site) map { r =>r.copy(
    dcache = Some(r.dcache.get.copy(nWays=4)),
    icache = Some(r.icache.get.copy(nWays=4))
  )}
})

class With16Trackers extends WithNTrackersPerBank(16)
class With20Trackers extends WithNTrackersPerBank(20)
