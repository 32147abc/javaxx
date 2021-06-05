/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE TABLE IF NOT EXISTS `useruser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `gender` varchar(32) DEFAULT NULL,
  `age` int(3) DEFAULT NULL,testuseruseruseruser
  `address` varchar(60) DEFAULT NULL,
  `qq` varchar(32) DEFAULT NULL,
  `email` varchar(32) DEFAULT NULL,
  `username` varchar(32) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `name`, `gender`, `age`, `address`, `qq`, `email`, `username`, `password`) VALUES
	(1, '塘沽路', '女', 106, NULL, NULL, NULL, 'user', '123456'),
	(4, '小8', '4', 14, 'bzd4', '28744', '1236@qq.com', 'users4', '123456'),
	(5, '小9', '5', 15, 'bzd5', '28745', '1237@qq.com', 'users5', '123456'),
	(17, 'admin', '男', 15, '陕西', '12', '54', NULL, NULL),
	(19, '小福子', '男', 100, '陕西', '22235142', '1223@12123', NULL, NULL),
	(20, '123', '男', 123, '上海', '123', '123', NULL, NULL),
	(21, '4', '男', 4, '上海', '4', '4', NULL, NULL),
	(22, '7', '男', 7, '上海', '7', '7', NULL, NULL),
	(23, '8', '男', 8, '陕西', '8', '8', NULL, NULL),
	(24, '12', '男', 12, '陕西', '12', '12', NULL, NULL),
	(25, '13', '男', 13, '陕西', '13', '13', NULL, NULL),
	(26, '14', '男', 14, '陕西', '14', '14', NULL, NULL),
	(27, '15', '男', 15, '陕西', '15', '15', NULL, NULL),
	(28, '', '男', 0, '陕西', '', '', NULL, NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
SELECT * FROM jone WHERE username = "admin" AND PASSWORD = "123"