//
//  RoomDetailView.swift
//  iosApp
//
//  Created by Alexander Dobrynin on 05.09.22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct RoomDetailView: View {
  
  let room: Room
  
  var body: some View {
    if let location = room.roomLocation {
      viewWithLocation(location)
    } else {
      roomInfo(buildingInfo: nil)
    }
  }
  
  private func roomInfo(buildingInfo: String?) -> some View {
    HStack(alignment: .top) {
      Spacer()
      VStack {
        Text(room.name)
          .font(.title)
        if !room.desc.isEmpty {
          Text(room.desc)
            .font(.subheadline)
        }
        if let buildingInfo = buildingInfo {
          Text(buildingInfo)
            .font(.subheadline)
        }
      }
      Spacer()
    }
  }
  
  private func viewWithLocation(_ location: RoomLocation) -> some View {
    GeometryReader { geo in
      VStack(alignment: .center) {
        roomInfo(buildingInfo: location.buildingInfo)
          .frame(maxHeight: roomNameMaxHeight(geo))
        RoomLocationImageView(url: location.url, maxHeight: roomImgMaxHeight(geo))
      }.padding()
    }
  }
  
  private func roomNameMaxHeight(_ geo: GeometryProxy) -> CGFloat {
    geo.size.height * 0.2
  }
  
  private func roomImgMaxHeight(_ geo: GeometryProxy) -> CGFloat {
    geo.size.height * 0.8
  }
}
