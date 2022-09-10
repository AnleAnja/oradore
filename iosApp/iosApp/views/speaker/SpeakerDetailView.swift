//
//  RoomDetailView.swift
//  iosApp
//
//  Created by Anja on 04.09.22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct SpeakerDetailView: View {
  let speaker: Speaker
  let screenHeight = UIScreen.main.bounds.size.height
  
  var body: some View {
    ScrollView {
      VStack(alignment: .leading) {
        speakerImageView()
        speakerBusinessDataView()
        speakerInfoView()
        speakerBioView()
      }
      .padding()
      .navigationBarTitleDisplayMode(.inline)
    }
  }
  
  @ViewBuilder
  private func speakerImageView() -> some View {
    if let url = speaker.imgLarge {
      AsyncImage(url: URL(string: url)) { image in
        image
          .resizable()
          .scaledToFill()
          .frame(maxHeight: screenHeight * 0.5)
          .clipped()
      } placeholder: {
        ProgressView()
      }
    }
  }
  
  @ViewBuilder
  private func speakerBusinessDataView() -> some View {
    Text("\(speaker.firstName) \(speaker.lastName)")
      .font(.title)
    if !speaker.company.isEmpty {
      Text(speaker.company)
        .font(.subheadline)
    }
    if !speaker.jobTitle.isEmpty {
      Text(speaker.jobTitle)
        .font(.subheadline)
    }
  }
  
  @ViewBuilder
  private func speakerInfoView() -> some View {
    VStack(alignment: .leading) {
      if !speaker.location.isEmpty {
        HStack {
          Image(systemName: "mappin")
          Text(speaker.location)
            .font(.caption)
        }
      }
      if !speaker.website.isEmpty {
        HStack {
          Image(systemName: "link")
          Text(speaker.website)
            .font(.caption)
        }.onTapGesture {
          if let url = URL(string: speaker.website) {
            UIApplication.shared.open(url)
          }
        }
      }
    }.padding(.top)
  }
  
  @ViewBuilder
  private func speakerBioView() -> some View {
    if !speaker.bio.isEmpty {
      Divider()
      Text(speaker.bio)
        .font(.body)
    }
  }
}
