//
//  SpeakerPreviewContent.swift
//  iosApp
//
//  Created by Alexander Dobrynin on 04.09.22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct SpeakerPreviewContent: View {
  let speaker: Speaker
  
  var body: some View {
    VStack(alignment: .leading) {
      Text("\(speaker.firstName) \(speaker.lastName)")
        .font(.headline)
      Text(speaker.company)
        .font(.caption)
        .foregroundColor(.secondary)
      Text(speaker.jobTitle)
        .font(.caption)
        .foregroundColor(.secondary)
    }
  }
}
