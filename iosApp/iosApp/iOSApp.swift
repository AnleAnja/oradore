import SwiftUI
import shared

@main
struct iOSApp: App {
  @StateObject private var viewModel = AppViewModel(
    api: NetworkApi(baseUrl: "http://advp44.gm.fh-koeln.de:9090")
  )
  
  var body: some Scene {
    WindowGroup {
      MainView()
        .environmentObject(viewModel)
    }
  }
}
