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
  let firstName: String
  let lastName: String
  let company: String
  let jobTitle: String
  
  var body: some View {
    VStack(alignment: .leading) {
      Text("\(firstName) \(lastName)")
        .font(.headline)
      Text(company)
        .font(.caption)
        .foregroundColor(.secondary)
      Text(jobTitle)
        .font(.caption)
        .foregroundColor(.secondary)
    }
  }
}
