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
    GeometryReader { geo in
      VStack(alignment: .center) {
        HStack(alignment: .top) {
          Spacer()
          VStack {
            Text(room.name)
              .font(.title)
            if !room.desc.isEmpty {
              Text(room.desc)
                .font(.subheadline)
            }
          }
          Spacer()
        }
        .frame(maxHeight: roomNameMaxHeight(geo))
        if let url = room.url {
          AsyncImage(url: URL(string: url)) { image in
            image
              .resizable()
              .aspectRatio(contentMode: .fill)
              .frame(maxHeight: roomImgMaxHeight(geo))
              .clipped()
          } placeholder: {
            ProgressView()
          }
        }
      }
      .padding()
    }
  }
  
  private func roomNameMaxHeight(_ geo: GeometryProxy) -> CGFloat {
    geo.size.height * 0.2
  }
  
  private func roomImgMaxHeight(_ geo: GeometryProxy) -> CGFloat {
    geo.size.height * 0.8
  }
}
