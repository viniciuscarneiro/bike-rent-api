package com.trio.java.bikerentapi.service.impl;

import com.trio.java.bikerentapi.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Mock implementation of the NotificationService for testing or development purposes.
 */
@Service
@Slf4j
public class NotificationMockImpl implements NotificationService {

  /**
   * Sends a notification to the specified email address with the given message.
   *
   * @param email   The recipient's email address.
   * @param message The content of the notification.
   */
  @Override
  public void sendNotification(String email, String message) {
    try {
      log.info("Notification sent to {}: \n{}", email, message);
    } catch (Exception e) {
      log.error("Error sending notification to {}: {}", email, e.getMessage());
    }
  }
}
