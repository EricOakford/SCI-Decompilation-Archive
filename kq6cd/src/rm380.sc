;;; Sierra Script 1.0 - (do not remove this comment)
(script# 380)
(include sci.sh)
(use Main)
(use NewRoomCue)
(use Kq6Procs)
(use Conv)
(use Scaler)
(use Motion)
(use Actor)
(use System)

(public
	rm380 0
)

(instance myConv of Conversation
	(properties)
)

(instance rm380 of KQ6Room
	(properties
		picture 380
		style $0007
		walkOffEdge 1
		autoLoad 0
	)
	
	(method (init)
		(super init: &rest)
		(theIconBar
			enable:
			disable: 0 1 2 3 4 5 6
			height: -100
			activateHeight: -100
		)
		(Cursor showCursor: 0)
		(theMusic number: 380 setLoop: -1 play:)
		(oracArm init: stopUpd:)
		(egoBod addToPic:)
		(egoButt addToPic:)
		(ego
			view: 3812
			normal: 0
			posn: 230 152
			cycleSpeed: 18
			setPri: 14
			init:
			stopUpd:
			setScript: goodNews
		)
		(theGame givePoints: 5)
	)
	
	(method (dispose)
		(theIconBar height: 0 activateHeight: 0)
		(Cursor showCursor: 1)
		(ego setScale: 0)
		(theIconBar enable: 6)
		(theGame setCursor: waitCursor)
		(super dispose:)
	)
)

(instance oracArm of Prop
	(properties
		x 94
		y 87
		view 3832
		signal $4000
		cycleSpeed 8
	)
)

(instance cassFace of Prop
	(properties
		x 182
		y 158
		view 384
		signal $4000
	)
)

(instance deadHead of Prop
	(properties
		x 164
		y 125
		view 3841
		signal $4000
		cycleSpeed 10
	)
)

(instance flyer of Actor
	(properties
		view 353
		signal $6000
	)
)

(instance egoBod of View
	(properties
		x 230
		y 152
		view 381
		priority 14
		signal $4010
	)
)

(instance egoButt of View
	(properties
		x 232
		y 191
		view 381
		loop 1
		priority 14
		signal $4010
	)
)

(instance goodNews of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 4))
			(1
				(myConv
					add: -1 1 0 1 2
					add: -1 1 0 1 3
					add: -1 1 0 1 4
					add: -1 1 0 1 5
					add: -1 1 0 1 6
					init: self
				)
			)
			(2 (= seconds 2))
			(3
				(oracArm view: 383 setCycle: CT 3 1 self)
				(UnLoad 128 3832)
			)
			(4
				(soundFx2 number: 381 setLoop: 1 play:)
				(oracArm setCycle: End self)
			)
			(5
				(oracArm view: 3832 cel: 0 stopUpd:)
				(UnLoad 128 383)
				(= seconds 1)
			)
			(6
				(cassFace init: setCycle: End self)
				(soundFx2 number: 382 setLoop: 1 play:)
			)
			(7
				(cassFace stopUpd:)
				(= ticks 6)
			)
			(8 (= seconds 2))
			(9
				(myConv
					add: -1 1 0 1 7
					add: -1 1 0 1 8
					add: -1 1 0 1 9
					init: self
				)
			)
			(10
				(cassFace startUpd: setCycle: Beg self)
			)
			(11
				(myConv
					add: -1 1 0 1 10
					add: -1 1 0 1 11
					add: -1 1 0 1 12
					add: -1 1 0 1 13
					add: -1 1 0 1 14
					add: -1 1 0 1 15
					add: -1 1 0 1 16
					add: -1 1 0 1 17
					init: self
				)
			)
			(12 (= seconds 2))
			(13
				(cassFace dispose:)
				(oracArm view: 383 setCycle: CT 3 1 self)
				(UnLoad 128 384)
				(UnLoad 128 3832)
			)
			(14
				(soundFx2 number: 381 setLoop: 1 play:)
				(oracArm setCycle: End self)
			)
			(15
				(oracArm view: 3832 cel: 0 stopUpd:)
				(UnLoad 128 383)
				(= seconds 1)
			)
			(16
				(deadHead init: setCycle: End self)
				(soundFx2 number: 383 setLoop: 1 play:)
			)
			(17
				(deadHead dispose:)
				(UnLoad 128 3841)
				(= cycles 6)
			)
			(18
				(myConv
					add: -1 1 0 1 18
					add: -1 1 0 1 19
					add: -1 1 0 1 20
					add: -1 1 0 1 21
					init: self
				)
			)
			(19
				(soundFx2 stop:)
				(= ticks 6)
			)
			(20
				(messager say: 1 0 1 22 self)
			)
			(21
				(oracArm view: 3831 posn: 158 82 setCycle: CT 5 1 self)
				(UnLoad 128 383)
			)
			(22
				(soundFx2 number: 924 setLoop: 1 play:)
				(oracArm view: 3831 posn: 158 82 setCycle: CT 10 1 self)
			)
			(23
				(ego
					view: 3811
					setLoop: 0
					cel: 2
					posn: (- (ego x?) 2) (ego y?)
				)
				(= cycles 6)
			)
			(24
				(ego setCycle: CT 3 1 self)
				(oracArm cel: 11)
			)
			(25 (ego cel: 4) (= cycles 6))
			(26
				(oracArm view: 3832 posn: 94 87 cel: 0 stopUpd:)
				(UnLoad 128 3831)
				(ego
					view: 3812
					cel: 0
					posn: (+ (ego x?) 2) (ego y?)
					stopUpd:
				)
				(= seconds 1)
			)
			(27
				(messager say: 1 0 1 23 self)
			)
			(28 (= cycles 6))
			(29
				(messager say: 1 0 1 24 self)
			)
			(30
				(Bset 3)
				(ego get: 40)
				(oracArm dispose:)
				(theGame givePoints: 1)
				(ego hide:)
				(egoBod dispose:)
				(egoButt dispose:)
				(curRoom setScript: flyToBeach)
			)
		)
	)
)

(instance flyToBeach of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(curRoom drawPic: 350 10)
				(flyer setScale: Scaler 5 4 190 0)
				(= ticks 4)
			)
			(1
				(flyer
					posn: 174 14
					setLoop: 1
					setCycle: Fwd
					setScale: Scaler 50 5 19 14
					init:
					setMotion: MoveTo 174 19 self
				)
			)
			(2
				(flyer
					setScale: Scaler 50 49 190 0
					setMotion: MoveTo 180 -10 self
				)
			)
			(3 (curRoom newRoom: 300))
		)
	)
)
