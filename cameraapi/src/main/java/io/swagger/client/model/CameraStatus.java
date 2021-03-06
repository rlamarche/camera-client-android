/**
 * Camera Toolbox API
 * Control any camera from unified API
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.swagger.client.model;

import java.util.*;
import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;

@ApiModel(description = "")
public class CameraStatus {
  
  @SerializedName("captureMode")
  private Integer captureMode = null;
  @SerializedName("aperture")
  private String aperture = null;
  @SerializedName("apertures")
  private List<String> apertures = null;
  @SerializedName("exposureMode")
  private String exposureMode = null;
  @SerializedName("exposureModes")
  private List<String> exposureModes = null;
  @SerializedName("focusMetering")
  private String focusMetering = null;
  @SerializedName("focusMeterings")
  private List<String> focusMeterings = null;
  @SerializedName("focusMode")
  private String focusMode = null;
  @SerializedName("focusModes")
  private List<String> focusModes = null;
  @SerializedName("iso")
  private String iso = null;
  @SerializedName("isos")
  private List<String> isos = null;
  @SerializedName("isoAuto")
  private Boolean isoAuto = null;
  @SerializedName("shutterSpeed")
  private String shutterSpeed = null;
  @SerializedName("shutterSpeeds")
  private List<String> shutterSpeeds = null;
  @SerializedName("isInLiveView")
  private Boolean isInLiveView = null;
  @SerializedName("isRecording")
  private Boolean isRecording = null;

  /**
   * capture mode (0 = photo, 1 = video)
   **/
  @ApiModelProperty(value = "capture mode (0 = photo, 1 = video)")
  public Integer getCaptureMode() {
    return captureMode;
  }
  public void setCaptureMode(Integer captureMode) {
    this.captureMode = captureMode;
  }

  /**
   * The current aperture
   **/
  @ApiModelProperty(value = "The current aperture")
  public String getAperture() {
    return aperture;
  }
  public void setAperture(String aperture) {
    this.aperture = aperture;
  }

  /**
   * available apertures
   **/
  @ApiModelProperty(value = "available apertures")
  public List<String> getApertures() {
    return apertures;
  }
  public void setApertures(List<String> apertures) {
    this.apertures = apertures;
  }

  /**
   * The current exposure mode
   **/
  @ApiModelProperty(value = "The current exposure mode")
  public String getExposureMode() {
    return exposureMode;
  }
  public void setExposureMode(String exposureMode) {
    this.exposureMode = exposureMode;
  }

  /**
   * available exposure modes
   **/
  @ApiModelProperty(value = "available exposure modes")
  public List<String> getExposureModes() {
    return exposureModes;
  }
  public void setExposureModes(List<String> exposureModes) {
    this.exposureModes = exposureModes;
  }

  /**
   * The current focus metering
   **/
  @ApiModelProperty(value = "The current focus metering")
  public String getFocusMetering() {
    return focusMetering;
  }
  public void setFocusMetering(String focusMetering) {
    this.focusMetering = focusMetering;
  }

  /**
   * available focus meterings
   **/
  @ApiModelProperty(value = "available focus meterings")
  public List<String> getFocusMeterings() {
    return focusMeterings;
  }
  public void setFocusMeterings(List<String> focusMeterings) {
    this.focusMeterings = focusMeterings;
  }

  /**
   * The current focus mode
   **/
  @ApiModelProperty(value = "The current focus mode")
  public String getFocusMode() {
    return focusMode;
  }
  public void setFocusMode(String focusMode) {
    this.focusMode = focusMode;
  }

  /**
   * available focus modes
   **/
  @ApiModelProperty(value = "available focus modes")
  public List<String> getFocusModes() {
    return focusModes;
  }
  public void setFocusModes(List<String> focusModes) {
    this.focusModes = focusModes;
  }

  /**
   * The current ISO
   **/
  @ApiModelProperty(value = "The current ISO")
  public String getIso() {
    return iso;
  }
  public void setIso(String iso) {
    this.iso = iso;
  }

  /**
   * available ISO
   **/
  @ApiModelProperty(value = "available ISO")
  public List<String> getIsos() {
    return isos;
  }
  public void setIsos(List<String> isos) {
    this.isos = isos;
  }

  /**
   * tell whether iso auto is enabled
   **/
  @ApiModelProperty(value = "tell whether iso auto is enabled")
  public Boolean getIsoAuto() {
    return isoAuto;
  }
  public void setIsoAuto(Boolean isoAuto) {
    this.isoAuto = isoAuto;
  }

  /**
   * The current shutter speed
   **/
  @ApiModelProperty(value = "The current shutter speed")
  public String getShutterSpeed() {
    return shutterSpeed;
  }
  public void setShutterSpeed(String shutterSpeed) {
    this.shutterSpeed = shutterSpeed;
  }

  /**
   * available shutter speeds
   **/
  @ApiModelProperty(value = "available shutter speeds")
  public List<String> getShutterSpeeds() {
    return shutterSpeeds;
  }
  public void setShutterSpeeds(List<String> shutterSpeeds) {
    this.shutterSpeeds = shutterSpeeds;
  }

  /**
   * tell whether the camera is in live view mode
   **/
  @ApiModelProperty(value = "tell whether the camera is in live view mode")
  public Boolean getIsInLiveView() {
    return isInLiveView;
  }
  public void setIsInLiveView(Boolean isInLiveView) {
    this.isInLiveView = isInLiveView;
  }

  /**
   * tell whether the camera is currently recording or not
   **/
  @ApiModelProperty(value = "tell whether the camera is currently recording or not")
  public Boolean getIsRecording() {
    return isRecording;
  }
  public void setIsRecording(Boolean isRecording) {
    this.isRecording = isRecording;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CameraStatus cameraStatus = (CameraStatus) o;
    return (this.captureMode == null ? cameraStatus.captureMode == null : this.captureMode.equals(cameraStatus.captureMode)) &&
        (this.aperture == null ? cameraStatus.aperture == null : this.aperture.equals(cameraStatus.aperture)) &&
        (this.apertures == null ? cameraStatus.apertures == null : this.apertures.equals(cameraStatus.apertures)) &&
        (this.exposureMode == null ? cameraStatus.exposureMode == null : this.exposureMode.equals(cameraStatus.exposureMode)) &&
        (this.exposureModes == null ? cameraStatus.exposureModes == null : this.exposureModes.equals(cameraStatus.exposureModes)) &&
        (this.focusMetering == null ? cameraStatus.focusMetering == null : this.focusMetering.equals(cameraStatus.focusMetering)) &&
        (this.focusMeterings == null ? cameraStatus.focusMeterings == null : this.focusMeterings.equals(cameraStatus.focusMeterings)) &&
        (this.focusMode == null ? cameraStatus.focusMode == null : this.focusMode.equals(cameraStatus.focusMode)) &&
        (this.focusModes == null ? cameraStatus.focusModes == null : this.focusModes.equals(cameraStatus.focusModes)) &&
        (this.iso == null ? cameraStatus.iso == null : this.iso.equals(cameraStatus.iso)) &&
        (this.isos == null ? cameraStatus.isos == null : this.isos.equals(cameraStatus.isos)) &&
        (this.isoAuto == null ? cameraStatus.isoAuto == null : this.isoAuto.equals(cameraStatus.isoAuto)) &&
        (this.shutterSpeed == null ? cameraStatus.shutterSpeed == null : this.shutterSpeed.equals(cameraStatus.shutterSpeed)) &&
        (this.shutterSpeeds == null ? cameraStatus.shutterSpeeds == null : this.shutterSpeeds.equals(cameraStatus.shutterSpeeds)) &&
        (this.isInLiveView == null ? cameraStatus.isInLiveView == null : this.isInLiveView.equals(cameraStatus.isInLiveView)) &&
        (this.isRecording == null ? cameraStatus.isRecording == null : this.isRecording.equals(cameraStatus.isRecording));
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + (this.captureMode == null ? 0: this.captureMode.hashCode());
    result = 31 * result + (this.aperture == null ? 0: this.aperture.hashCode());
    result = 31 * result + (this.apertures == null ? 0: this.apertures.hashCode());
    result = 31 * result + (this.exposureMode == null ? 0: this.exposureMode.hashCode());
    result = 31 * result + (this.exposureModes == null ? 0: this.exposureModes.hashCode());
    result = 31 * result + (this.focusMetering == null ? 0: this.focusMetering.hashCode());
    result = 31 * result + (this.focusMeterings == null ? 0: this.focusMeterings.hashCode());
    result = 31 * result + (this.focusMode == null ? 0: this.focusMode.hashCode());
    result = 31 * result + (this.focusModes == null ? 0: this.focusModes.hashCode());
    result = 31 * result + (this.iso == null ? 0: this.iso.hashCode());
    result = 31 * result + (this.isos == null ? 0: this.isos.hashCode());
    result = 31 * result + (this.isoAuto == null ? 0: this.isoAuto.hashCode());
    result = 31 * result + (this.shutterSpeed == null ? 0: this.shutterSpeed.hashCode());
    result = 31 * result + (this.shutterSpeeds == null ? 0: this.shutterSpeeds.hashCode());
    result = 31 * result + (this.isInLiveView == null ? 0: this.isInLiveView.hashCode());
    result = 31 * result + (this.isRecording == null ? 0: this.isRecording.hashCode());
    return result;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class CameraStatus {\n");
    
    sb.append("  captureMode: ").append(captureMode).append("\n");
    sb.append("  aperture: ").append(aperture).append("\n");
    sb.append("  apertures: ").append(apertures).append("\n");
    sb.append("  exposureMode: ").append(exposureMode).append("\n");
    sb.append("  exposureModes: ").append(exposureModes).append("\n");
    sb.append("  focusMetering: ").append(focusMetering).append("\n");
    sb.append("  focusMeterings: ").append(focusMeterings).append("\n");
    sb.append("  focusMode: ").append(focusMode).append("\n");
    sb.append("  focusModes: ").append(focusModes).append("\n");
    sb.append("  iso: ").append(iso).append("\n");
    sb.append("  isos: ").append(isos).append("\n");
    sb.append("  isoAuto: ").append(isoAuto).append("\n");
    sb.append("  shutterSpeed: ").append(shutterSpeed).append("\n");
    sb.append("  shutterSpeeds: ").append(shutterSpeeds).append("\n");
    sb.append("  isInLiveView: ").append(isInLiveView).append("\n");
    sb.append("  isRecording: ").append(isRecording).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
