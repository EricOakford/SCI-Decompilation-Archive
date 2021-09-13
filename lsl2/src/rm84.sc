;;; Sierra Script 1.0 - (do not remove this comment)
(script# 84)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Jump)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm84 0
)

(instance rm84 of Room
	(properties
		picture 84
	)
	
	(method (init)
		(Load VIEW 197)
		(Load VIEW 817)
		(Load VIEW 818)
		(Load VIEW 831)
		(Load VIEW 832)
		(Load VIEW 833)
		(Load VIEW 834)
		(Load VIEW 835)
		(Load VIEW 198)
		(Load SOUND 104)
		(Load SOUND 112)
		(Load SOUND 10)
		(theMusic init:)
		(Load SCRIPT JUMP)
		(super init:)
		(self setScript: rm84Script)
		((View new:)
			view: 817
			ignoreActors:
			posn: 190 116
			addToPic:
		)
		((View new:)
			view: 817
			ignoreActors:
			loop: 0
			cel: 1
			posn: 216 72
			setPri: 0
			addToPic:
		)
		((View new:)
			view: 817
			ignoreActors:
			loop: 0
			cel: 2
			posn: 103 71
			setPri: 0
			addToPic:
		)
		((View new:)
			view: 817
			ignoreActors:
			loop: 0
			cel: 3
			posn: 21 176
			setPri: 15
			addToPic:
		)
		((View new:)
			view: 817
			ignoreActors:
			cel: 4
			posn: 60 109
			addToPic:
		)
		(aFlames
			ignoreActors:
			setPri: 11
			init:
			stopUpd:
		)
		(aComputerWest
			ignoreActors:
			init:
			stopUpd:
		)
		(aComputerEast
			ignoreActors:
			init:
			stopUpd:
		)
		(aLaser
			ignoreActors:
			setPri: 2
			init:
			stopUpd:
		)
		(aLaserFire
			ignoreActors:
			setPri: 3
			init:
		)
		(aTrapdoor
			ignoreActors:
			setPri: 15
			init:
			stopUpd:
		)
		(aHench0
			ignoreActors:
			init:
			stopUpd:
		)
		(aHench1
			ignoreActors:
			init:
			stopUpd:
		)
		(aHench2
			ignoreActors:
			init:
			stopUpd:
		)
		(aHench3
			ignoreActors:
			init:
			stopUpd:
		)
		(aPatty
			ignoreActors:
			setCycle: Forward
			setPri: 10
			cycleSpeed: 2
			init:
		)
		(aPiano
			setLoop: 8
			ignoreActors:
			setPri: 9
			init:
			stopUpd:
		)
		(aColumn
			setLoop: 7
			ignoreActors:
			init:
			stopUpd:
		)
		(aChandelier
			setLoop: 0
			ignoreActors:
			setPri: 11
			init:
			stopUpd:
		)
		(aBullet
			ignoreActors:
			setLoop: 6
			setPri: 15
			init:
		)
		(aNo
			ignoreActors:
			init:
			stopUpd:
		)
		(= currentStatus egoWONGAME)
		(HandsOff)
		(ego
			view: 197
			ignoreActors:
			illegalBits: 0
			setLoop: 0
			posn: 0 120
			setCycle: Forward
			setPri: 8
			setStep: 10 10
			init:
		)
	)
)

(instance aFlames of Prop
	(properties
		y 130
		x 34
		view 835
	)
)

(instance aComputerWest of Prop
	(properties
		y 80
		x 81
		view 817
		loop 2
	)
)

(instance aComputerEast of Prop
	(properties
		y 80
		x 236
		view 817
		loop 3
	)
)

(instance aLaser of Prop
	(properties
		y 44
		x 151
		view 817
		loop 4
	)
)

(instance aLaserFire of Prop
	(properties
		y 1040
		x 161
		view 817
		loop 5
	)
)

(instance aTrapdoor of Prop
	(properties
		y 184
		x 294
		view 817
		loop 1
	)
)

(instance aHench0 of Prop
	(properties
		y 112
		x 154
		view 833
	)
)

(instance aHench1 of Prop
	(properties
		y 107
		x 168
		view 833
		loop 1
	)
)

(instance aHench2 of Prop
	(properties
		y 103
		x 184
		view 833
		loop 2
	)
)

(instance aHench3 of Prop
	(properties
		y 99
		x 198
		view 833
		loop 3
	)
)

(instance aPatty of Prop
	(properties
		y 121
		x 206
		view 831
	)
)

(instance aPiano of Actor
	(properties
		y 124
		x 234
		yStep 8
		view 817
		xStep 8
	)
)

(instance aColumn of Actor
	(properties
		y 25
		x 230
		yStep 10
		view 817
		xStep 10
	)
)

(instance aChandelier of Actor
	(properties
		y 39
		x 204
		yStep 10
		view 817
		cel 5
		xStep 10
	)
)

(instance aBullet of Actor
	(properties
		y 999
		x 999
		yStep 22
		view 817
		illegalBits $0000
		xStep 22
	)
)

(instance aNo of Actor
	(properties
		y 129
		x 86
		view 818
	)
)

(instance rm84Script of Script
	(method (doit)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds 3)
			)
			(1
				(Print 84 0 #at -1 15 #width 280)
				(theMusic play: self)
				(Print 84 1 #at -1 20 #dispose)
				(= seconds 30)
			)
			(2
				(= seconds 0)
				(cls)
				(Print 84 2 #at -1 20 #dispose)
				(= seconds 30)
			)
			(3
				(= seconds 0)
				(cls)
				(Print 84 1 #at -1 20 #dispose)
				(= cycles 20)
			)
			(4
				(= seconds 0)
				(cls)
				(= seconds 30)
			)
			(5
				(= seconds 0)
				(theMusic dispose:)
				(Print 84 3 #at -1 15 #width 280)
				(theMusic number: 112 loop: -1 play:)
				(ego setMotion: MoveTo 155 121 self)
				(aPatty setLoop: 1 stopUpd:)
			)
			(6
				(ego setMotion: MoveTo 162 94 self)
			)
			(7
				(ego
					setLoop: 1
					setCel: 0
					setCycle: EndLoop
					setStep: 2 12
					setMotion: MoveTo 172 35 self
				)
			)
			(8
				(aChandelier hide:)
				(ego
					posn: 204 39
					setLoop: 2
					setCel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(9
				(ego setLoop: 3 setCel: 0 setCycle: Forward)
				(= cycles 24)
			)
			(10
				(aChandelier show: addToPic:)
				(ego
					setPri: -1
					setLoop: 4
					setCel: 0
					setCycle: Forward
					cycleSpeed: 0
					posn: 195 51
					setStep: 11 11
					setMotion: MoveTo 198 80 self
				)
			)
			(11
				(ego
					posn: 196 71
					setStep: 3 2
					setLoop: 5
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(12
				(ego
					posn: 198 83
					setLoop: 6
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(13
				(Print 84 4 #at -1 15 #width 280 #draw)
				(aComputerEast setCycle: EndLoop)
				(ego
					view: 198
					posn: 211 83
					setLoop: 0
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(14
				(Print 84 5 #at -1 15 #width 280 #draw)
				(aComputerWest setCycle: EndLoop self)
			)
			(15
				(Print 84 6 #at -1 20 #draw)
				(Print 84 7 #at -1 152)
				(aComputerEast addToPic:)
				(aComputerWest addToPic:)
				(ego
					setLoop: 1
					setStep: 2 2
					setCycle: Walk
					setMotion: MoveTo 111 94
				)
				(aLaser setCycle: EndLoop self)
			)
			(16
				(aLaser addToPic:)
				(aLaserFire posn: 161 39 setCycle: EndLoop)
				(aBullet
					posn: 162 41
					setCel: 0
					setMotion: MoveTo 277 48 self
				)
			)
			(17
				(aBullet setCycle: EndLoop self)
			)
			(18
				(aLaserFire cel: 0 setCycle: EndLoop)
				(aBullet
					posn: 162 41
					setCel: 0
					setMotion: MoveTo 160 4 self
				)
			)
			(19
				(aBullet setCycle: EndLoop self)
			)
			(20
				(aLaserFire cel: 0 setCycle: EndLoop)
				(aBullet
					posn: 162 41
					setCel: 0
					setMotion: MoveTo 218 78 self
				)
			)
			(21
				(aBullet setCycle: EndLoop self)
			)
			(22
				(aLaserFire cel: 0 setCycle: EndLoop)
				(aBullet
					posn: 162 41
					setCel: 0
					setMotion: MoveTo 283 157 self
				)
			)
			(23
				(aBullet setCycle: EndLoop self)
			)
			(24
				(aTrapdoor setCycle: EndLoop)
				(aLaserFire cel: 0 setCycle: EndLoop)
				(aBullet
					posn: 162 41
					setCel: 0
					setMotion: MoveTo 119 181 self
				)
			)
			(25
				(aBullet setCycle: EndLoop self)
			)
			(26
				(aTrapdoor dispose:)
				(aLaserFire cel: 0 setCycle: EndLoop)
				(aBullet
					posn: 162 41
					setCel: 0
					setMotion: MoveTo 33 88 self
				)
			)
			(27
				(aBullet setCycle: EndLoop self)
			)
			(28
				(aLaserFire cel: 0 setCycle: EndLoop)
				(aBullet
					posn: 162 41
					setCel: 0
					setMotion: MoveTo 250 10 self
				)
			)
			(29
				(aBullet setCycle: EndLoop self)
			)
			(30
				(aLaserFire cel: 0 setCycle: EndLoop)
				(aBullet
					posn: 162 41
					setCel: 0
					setMotion: MoveTo 157 116 self
				)
			)
			(31
				(aBullet setCycle: EndLoop self)
			)
			(32
				(aLaserFire cel: 0 setCycle: EndLoop)
				(aBullet
					posn: 162 41
					setCel: 0
					setMotion: MoveTo 31 110 self
				)
			)
			(33
				(aBullet setCycle: EndLoop self)
			)
			(34
				(aLaserFire cel: 0 setCycle: EndLoop)
				(aBullet
					posn: 162 41
					setCel: 0
					setMotion: MoveTo 117 15 self
				)
			)
			(35
				(aBullet setCycle: EndLoop self)
			)
			(36
				(aLaserFire cel: 0 setCycle: EndLoop)
				(aBullet
					posn: 162 41
					setCel: 0
					setMotion: MoveTo 148 5 self
				)
			)
			(37
				(aBullet setCycle: EndLoop self)
			)
			(38
				(aLaserFire cel: 0 setCycle: EndLoop)
				(aBullet
					posn: 162 41
					setCel: 0
					setMotion: MoveTo 221 22 self
				)
			)
			(39
				(aBullet setCycle: EndLoop self)
			)
			(40
				(Print 84 8 #at -1 15 #width 280)
				(aLaserFire posn: 999 999)
				(aBullet dispose:)
				(ego stopUpd:)
				(aColumn setCycle: EndLoop setMotion: JumpTo 255 83 self)
			)
			(41
				(ShakeScreen 1 1)
				(aPiano setPri: 10 setMotion: JumpTo 86 140 self)
			)
			(42
				(aNo
					view: 834
					setLoop: 0
					setCel: 0
					setPri: 1
					posn: 86 131
				)
				(aPiano setPri: 2 setCycle: EndLoop self)
				(theMusic dispose:)
				(theMusic number: 10 loop: 1 play:)
				(ShakeScreen 3 2)
			)
			(43
				(aPiano addToPic:)
				(= seconds 3)
			)
			(44
				(theGame changeScore: 30)
				(Print 84 9 #at -1 15 #width 280)
				(= seconds 4)
			)
			(45
				(aNo setCycle: EndLoop self)
				(theMusic number: 16 loop: -1 play:)
			)
			(46
				(aNo
					setLoop: 1
					setPri: 14
					moveSpeed: 3
					setStep: 1 1
					setCycle: Forward
					setMotion: MoveTo 89 112 self
				)
			)
			(47
				(aFlames setCycle: EndLoop self)
			)
			(48
				(aNo dispose:)
				(theMusic stop:)
				(aFlames setLoop: 1 setCel: 0 setCycle: EndLoop self)
			)
			(49
				(aFlames dispose:)
				(= seconds 3)
			)
			(50
				(ego view: 100 setLoop: 0 setStep: 3 2 setLoop: -1)
				(= seconds 2)
			)
			(51
				(Print 84 10 #at -1 15 #width 280)
				(Print 84 11 #at -1 15 #width 280)
				(= seconds 2)
			)
			(52
				(Print 84 12 #at -1 15 #width 280)
				(= seconds 3)
			)
			(53
				(Print 84 13 #at -1 15 #width 280)
				(aLaserFire
					setPri: 14
					posn: (- (aHench0 x?) 2) (- (aHench0 y?) 31)
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(54
				(aLaserFire posn: 999 999)
				(aHench0 cycleSpeed: 2 setCycle: EndLoop self)
			)
			(55
				(aHench0 stopUpd:)
				(aLaserFire
					posn: (- (aHench1 x?) 2) (- (aHench1 y?) 32)
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(56
				(aLaserFire posn: 999 999)
				(aHench1 cycleSpeed: 2 setCycle: EndLoop self)
			)
			(57
				(aHench1 stopUpd:)
				(aLaserFire
					posn: (- (aHench2 x?) 2) (- (aHench2 y?) 30)
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(58
				(aLaserFire posn: 999 999)
				(aHench2 cycleSpeed: 2 setCycle: EndLoop self)
			)
			(59
				(aHench2 stopUpd:)
				(aLaserFire
					posn: (- (aHench3 x?) 2) (- (aHench3 y?) 32)
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(60
				(aLaserFire posn: 999 999)
				(aHench3 cycleSpeed: 2 setCycle: EndLoop self)
			)
			(61
				(aHench3 stopUpd:)
				(Print 84 14 #at -1 15 #width 280)
				(Print 84 15 #at -1 15 #width 280)
				(Print 84 16 #at -1 20)
				(Print 84 17 #at -1 20)
				(ego setLoop: -1 setMotion: MoveTo 79 110 self)
			)
			(62
				(ego loop: 1 forceUpd:)
				(= seconds 3)
			)
			(63
				(Print 84 18 #at -1 15 #width 280)
				(Print 84 19 #at -1 15 #width 280)
				(Print 84 20 #at -1 152)
				(Print 84 21 #at -1 15 #width 280)
				(Print 84 22 #at -1 152)
				(Print 84 23 #at -1 20)
				(= seconds 3)
			)
			(64
				(Print 84 24 #at -1 20)
				(Print 84 25 #at -1 20)
				(Print 84 26 #at -1 20)
				(= seconds 3)
			)
			(65
				(Print 84 24 #at -1 20)
				(Print 84 25 #at -1 20)
				(Print 84 26 #at -1 20)
				(Print 84 27 #at -1 152)
				(= seconds 3)
			)
			(66
				(Print 84 28 #at -1 15 #width 280)
				(= seconds 3)
			)
			(67
				(Print 84 29 #at -1 15 #width 280)
				(= seconds 3)
			)
			(68
				(Print 84 30 #at -1 15 #width 280)
				(Print 84 31 #at -1 15 #width 280)
				(= seconds 3)
			)
			(69
				(Print 84 32 #at -1 15 #width 280)
				(= seconds 3)
			)
			(70
				(Print 84 33 #at -1 15 #width 280)
				(Print 84 34 #at -1 20)
				(= cycles 20)
			)
			(71
				(ego setLoop: -1 setMotion: MoveTo 93 (ego y?) self)
			)
			(72
				(ego setMotion: MoveTo 93 92 self)
			)
			(73
				(ego setMotion: MoveTo 89 92 self)
			)
			(74
				(Print 84 35 #at -1 15 #width 280)
				(Print 84 36 #at -1 15 #width 280)
				(= seconds 3)
			)
			(75
				(Print 84 37 #at -1 15 #width 280)
				(= cycles 20)
			)
			(76
				(ego setMotion: MoveTo 133 100 self)
			)
			(77
				(aHench0 dispose:)
				(aHench1 dispose:)
				(aHench2 dispose:)
				(aHench3 dispose:)
				(ego
					view: 832
					setLoop: 0
					setStep: 2 2
					setPri: 14
					posn: 176 112
					setMotion: MoveTo 124 130 self
				)
			)
			(78
				(ego setLoop: 1 setMotion: MoveTo 333 214 self)
				(aPatty setLoop: 2)
				(Print 84 38 #at -1 15 #width 280 #draw)
				(Print 84 39 #at -1 15 #width 280)
				(Print 84 40 #at -1 15 #width 280)
				(= cycles 12)
			)
			(79
				(Print 84 41 #at -1 152)
				(Print 84 42 #at -1 20)
			)
			(80
				(Print 84 43)
				(Print 84 44)
				(Print 84 45)
				(= seconds 3)
			)
			(81
				(Print 84 46)
				(= endGameState 100)
				(curRoom newRoom: 92)
			)
		)
	)
)

(instance theMusic of Sound
	(properties
		number 104
	)
)
