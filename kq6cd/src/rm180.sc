;;; Sierra Script 1.0 - (do not remove this comment)
(script# 180)
(include sci.sh)
(use Main)
(use NewRoomCue)
(use Motion)
(use Actor)
(use System)

(public
	rm180 0
)

(local
	[local0 100]
)
(instance rm180 of KQ6Room
	(properties
		picture 180
		autoLoad 0
	)
	
	(method (init)
		(super init: &rest)
		(if (== prevRoomNum 99)
			(theMusic number: 752 setLoop: -1 play:)
		)
		(ego
			init:
			view: 180
			normal: 0
			cel: 0
			posn: 86 94
			setPri: 14
		)
		(self setScript: sKissStuff)
	)
)

(instance sKissStuff of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= seconds 2)
			)
			(1 (ego setCycle: CT 2 1 self))
			(2 (= ticks 60))
			(3 (ego cel: 3) (= ticks 30))
			(4 (ego cel: 2) (= ticks 30))
			(5 (ego cel: 3) (= ticks 30))
			(6
				((ScriptID 1015 6) talkWidth: 100 x: 170 y: 140)
				(= seconds 5)
			)
			(7 (messager say: 1 0 3 1 self))
			(8
				(ego setCycle: Beg self)
				((ScriptID 1015 6) talkWidth: 150 x: 70 y: 40)
			)
			(9 (= seconds 2))
			(10
				(ego
					view: 758
					setLoop: 0
					cel: 3
					posn: 29 179
					scaleSignal: 1
					scaleX: 128
					scaleY: 128
					signal: 26624
				)
				(body init: setLoop: 0 cel: 0)
				(leftGuard init: cel: 2)
				(rightGuard init:)
				(sword init: setPri: 1 ignoreActors: 1 addToPic:)
				(curRoom drawPic: 750 10)
				(theIconBar disable: 6)
				(= cycles 2)
			)
			(11 (body setCycle: End self))
			(12 (ego setCycle: Beg self))
			(13
				(ego
					reset: 0
					scaleSignal: 1
					scaleX: 115
					scaleY: 115
					setPri: 13
					posn: 21 177
				)
				(cassima init: setPri: 14 setCycle: Beg self)
			)
			(14
				(cassima view: 784 setLoop: 0 posn: 29 188 addToPic:)
				(ego addToPic:)
				(= cycles 2)
			)
			(15
				(messager say: 1 0 2 1 self)
			)
			(16
				(cassima dispose:)
				(ego dispose:)
				(body setCycle: End self)
			)
			(17
				(messager say: 1 0 2 2 self)
			)
			(18 (body setCycle: End self))
			(19
				(messager say: 1 0 2 3 self)
			)
			(20
				(body setCycle: End self)
				(rightGuard cel: 3)
			)
			(21
				(messager say: 1 0 2 4 self)
			)
			(22
				(leftGuard cel: 0)
				(body setLoop: 1 cel: 0 setCycle: End self)
			)
			(23
				(body setLoop: 2 cel: 0 setCycle: End self)
			)
			(24 (= ticks 30))
			(25
				(body setCycle: CT 1 -1 self)
			)
			(26 (body setCycle: End self))
			(27 (body setCycle: Beg self))
			(28
				(rightGuard
					setLoop: 3
					setCycle: Walk
					setMotion: MoveTo 233 133 self
				)
			)
			(29
				(rightGuard
					setLoop: 0
					setPri: 1
					setMotion: MoveTo 249 135 self
				)
			)
			(30
				(rightGuard
					setLoop: 2
					setMotion: MoveTo 289 184 rightGuard
				)
				(body
					view: 145
					scaleSignal: 1
					scaleX: 95
					scaleY: 95
					setStep: 4 4
					posn: 169 149
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo 233 133 self
				)
			)
			(31
				(body setPri: 1 setMotion: MoveTo 249 135 self)
				(leftGuard
					view: 765
					setLoop: 0
					cel: 0
					posn: 115 150
					setCycle: End
				)
				(theGlobalSound number: 0 stop:)
				(theGlobalSound number: 652 setLoop: 1 play:)
			)
			(32
				(body setLoop: 2 setMotion: MoveTo 289 184 body)
				(leftGuard
					view: 7651
					posn: 127 150
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo 233 133 self
				)
			)
			(33
				(leftGuard setPri: 1 setMotion: MoveTo 249 135 self)
			)
			(34
				(leftGuard setLoop: 2 setMotion: MoveTo 289 184 self)
			)
			(35
				(leftGuard dispose:)
				(= cycles 2)
			)
			(36
				(curRoom drawPic: 98 9)
				(theIconBar enable: 6)
				(theMusic fade:)
				(= seconds 4)
			)
			(37
				(Message msgGET 180 1 0 2 5 @local0)
				(curRoom drawPic: 98 12)
				(Display @local0 dsCOORD 85 85 dsCOLOR 14 dsFONT 0)
				(theMusic number: 743 setLoop: -1 play:)
				(= seconds 3)
			)
			(38 (curRoom newRoom: 740))
		)
	)
)

(instance cassima of Actor
	(properties
		x 29
		y 178
		view 753
		loop 4
		cel 3
		signal $4000
		illegalBits $0000
	)
)

(instance leftGuard of Actor
	(properties
		x 115
		y 150
		view 724
		loop 4
	)
)

(instance rightGuard of Actor
	(properties
		x 204
		y 161
		view 726
		loop 4
		cel 1
	)
	
	(method (cue)
		(super cue:)
		(self dispose:)
	)
)

(instance body of Actor
	(properties
		x 159
		y 150
		view 757
		loop 2
		cel 1
	)
	
	(method (cue)
		(super cue:)
		(self dispose:)
	)
)

(instance sword of View
	(properties
		x 157
		y 143
		view 757
		loop 3
	)
)
