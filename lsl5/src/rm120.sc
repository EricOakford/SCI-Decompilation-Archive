;;; Sierra Script 1.0 - (do not remove this comment)
(script# 120)
(include game.sh)
(use Main)
(use LLRoom)
(use Talker)
(use LoadMany)
(use Reverse)
(use Actor)
(use System)

(public
	rm120 0
)

(local
	local0
)
(instance rm120 of LLRoom
	(properties
		picture 120
	)
	
	(method (init)
		(LoadMany VIEW 120 1122 1123 124)
		(LoadMany SOUND 120)
		(HandsOff)
		(super init:)
		(Vinnie_a init: setPri: 10 addToPic:)
		(bossHand
			init:
			cycleSpeed: 6
			setCel: (bossHand lastCel:)
			stopUpd:
		)
		(bossSmoke init: cycleSpeed: 24 stopUpd:)
		(bossRtBrow init: cycleSpeed: 24 stopUpd:)
		(bossLtBrow init: cycleSpeed: 24 stopUpd:)
		(addToPics doit:)
		(SetFFRoom 155)
		(theMusic number: 120 flags: 1 setLoop: -1 play:)
		(self setScript: sCartoon)
	)
	
	(method (dispose)
		(super dispose: &rest)
		(theMusic stop:)
	)
)

(instance slide of Prop
	(properties
		x 130
		y 80
		view 120
	)
)

(instance Vinnie_a of View
	(properties
		x 68
		y 65
		view 1122
		loop 1
	)
)

(instance Vinnie_b of Talker
	(properties
		x 1
		y 180
		nsTop 66
		nsLeft 67
		view 1122
		loop 3
		talkWidth 280
		name "Vinnie"
	)
	
	(method (init)
		(= mouth goon1Mouth)
		(super init: &rest)
	)
)

(instance goon1Mouth of Prop
	(properties
		nsTop 30
		nsLeft 24
		view 1122
		cycleSpeed 5
	)
)

(instance Bruno_a of Prop
	(properties
		x 283
		y 115
		view 1123
	)
)

(instance Bruno_b of Talker
	(properties
		x 115
		y 180
		nsTop 95
		nsLeft 267
		view 1123
		loop 3
		talkWidth 200
		name "Bruno"
	)
	
	(method (init)
		(= mouth goon2Mouth)
		(super init: &rest)
	)
)

(instance goon2Mouth of Prop
	(properties
		view 1123
		cycleSpeed 5
	)
)

(instance bossHand of Prop
	(properties
		x 156
		y 150
		view 124
		loop 4
	)
)

(instance bossSmoke of Prop
	(properties
		x 167
		y 91
		view 124
		loop 3
	)
)

(instance bossRtBrow of Prop
	(properties
		x 227
		y 57
		view 124
		loop 1
	)
)

(instance bossLtBrow of Prop
	(properties
		x 213
		y 57
		view 124
	)
)

(instance sCartoon of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(Say Vinnie_b 120 0 #dispose #caller self)
			)
			(2 (= ticks 123))
			(3
				(slide init: stopUpd:)
				(= cycles 2)
			)
			(4
				(Say Vinnie_b 120 1 #dispose #caller self)
			)
			(5 (= ticks 60))
			(6
				(Say Vinnie_b 120 2 #dispose #caller self)
			)
			(7 (= ticks 60))
			(8
				(Vinnie_b talkWidth: 120)
				(Say Vinnie_b 120 3 #dispose #caller self)
			)
			(9
				(bossHand
					setCel: (bossHand lastCel:)
					startUpd:
					cycleSpeed: 18
					setCycle: Reverse
				)
				(bossSmoke startUpd: setCycle: Reverse)
				(= ticks 240)
			)
			(10
				(bossHand
					setCel: (bossHand lastCel:)
					stopUpd:
					setCycle: 0
				)
				(bossSmoke stopUpd: setCycle: 0)
				(bossRtBrow stopUpd: setCycle: 0)
				(bossLtBrow stopUpd: setCycle: 0)
				(= cycles 5)
			)
			(11
				(Say Bruno_b 120 4 #dispose #caller self)
			)
			(12 (= ticks 60))
			(13
				(slide startUpd: cel: 1)
				(= cycles 2)
			)
			(14
				(Say Bruno_b 120 5 #dispose #caller self)
			)
			(15 (= ticks 60))
			(16
				(Say Bruno_b 120 6 #dispose #caller self)
			)
			(17 (= ticks 60))
			(18
				(Say Bruno_b 120 7 #dispose #caller self)
			)
			(19 (= ticks 60))
			(20
				(slide cel: 2)
				(bossHand
					startUpd:
					setCel: (bossHand lastCel:)
					setCycle: Reverse
				)
				(bossSmoke startUpd: setCycle: Reverse)
				(= ticks 240)
			)
			(21
				(bossHand
					setCel: (bossHand lastCel:)
					stopUpd:
					setCycle: 0
				)
				(bossSmoke stopUpd: setCycle: 0)
				(bossRtBrow stopUpd: setCycle: 0)
				(bossLtBrow stopUpd: setCycle: 0)
				(= cycles 5)
			)
			(22
				(Say Bruno_b 120 8 #dispose #caller self)
			)
			(23 (= ticks 60))
			(24
				(Say Bruno_b 120 9 #dispose #caller self)
			)
			(25 (= ticks 60))
			(26
				(Say Bruno_b 120 10 #dispose #caller self)
			)
			(27 (= ticks 60))
			(28
				(Say Bruno_b 120 11 #dispose #caller self)
			)
			(29 (= ticks 60))
			(30 (curRoom newRoom: 130))
		)
	)
)
