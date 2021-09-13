;;; Sierra Script 1.0 - (do not remove this comment)
(script# 22)
(include game.sh)
(use Main)
(use Intrface)
(use Avoider)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm22 0
)

(local
	local0
	henchmanIsHere
)
(instance rm22 of Room
	(properties
		picture 22
		horizon 127
		north 18
		south 25
		west 21
	)
	
	(method (init)
		(Load VIEW 252)
		(Load VIEW 235)
		(super init:)
		(addToPics
			add: aView1 aView2 aView3 aView4 aView5 aView6 aView7
			doit:
		)
		(aSpudsSign1
			setPri: 12
			cycleSpeed: 4
			setCycle: Forward
			init:
		)
		(aSpudsSign2
			setPri: 12
			cycleSpeed: 4
			setCycle: Forward
			init:
		)
		(aSpudsSign3
			setPri: 12
			cycleSpeed: 4
			setCycle: Forward
			init:
		)
		(aBoat
			setPri: 7
			illegalBits: 0
			ignoreHorizon:
			setCycle: Forward
			init:
			moveSpeed: 1
			cycleSpeed: 1
			setStep: 1 1
			setScript: boatScript
		)
		(self setRegions: CITY setScript: rm22Script)
		(if (ego has: iOnklunk)
			(= henchmanIsHere TRUE)
			(rm22Script changeState: 1)
			(Load VIEW 102)
			(Load VIEW 234)
			(Load SOUND 106)
			(theSound init:)
			(aHench
				illegalBits: cWHITE
				setCycle: Walk
				init:
				hide:
			)
			(aStars
				setCycle: Forward
				setPri: 13
				init:
				hide:
			)
		)
		(cond 
			((== prevRoomNum 0)
				(ego posn: 302 187)
			)
			((== prevRoomNum 18)
				(ego posn: 258 128)
			)
			((== prevRoomNum 26)
				(ego posn: 302 187)
			)
		)
		(NormalEgo)
		(ego init:)
	)
)

(instance rm22Script of Script
	(method (doit)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(= seconds (Random 3 5))
			)
			(2
				(aHench show: setMotion: MoveTo 127 175 self)
				(theSound play:)
			)
			(3
				(aHench
					setLoop: (if (< (aHench x?) (ego x?)) 0 else 1)
				)
				(Print 22 18 #draw)
			)
			(4
				(HandsOff)
				(= currentStatus egoSTOPPED)
				(if (< (aHench x?) (ego x?))
					(ego
						setAvoider: Avoider
						ignoreActors:
						setMotion: MoveTo (+ (aHench x?) 20) (+ 1 (aHench y?)) self
					)
				else
					(ego
						setAvoider: Avoider
						ignoreActors:
						setMotion: MoveTo (- (aHench x?) 20) (+ 1 (aHench y?)) self
					)
				)
			)
			(5
				(ego
					view: 102
					setLoop: (if (< (aHench x?) (ego x?)) 1 else 0)
					cel: 0
					setCycle: EndLoop self
				)
				(= local0 (if (< (aHench x?) (ego x?)) 0 else 1))
				(aHench setLoop: (+ 2 local0) setCycle: EndLoop)
			)
			(6
				(aHench stopUpd:)
				(ego
					setLoop: (+ 2 (ego loop?))
					cel: 0
					setCycle: EndLoop self
				)
			)
			(7
				(= seconds 3)
			)
			(8
				(Print 22 19)
				(= seconds 3)
			)
			(9
				(ego setCycle: CycleTo 3 1 self)
			)
			(10
				(ego setCycle: EndLoop self)
			)
			(11
				(Print 22 20)
				(= seconds 3)
			)
			(12
				(ego
					setLoop: (+ 2 (ego loop?))
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(13
				(Print 22 21 #at -1 123)
				(= seconds 3)
			)
			(14
				(if (== (ego loop?) 4)
					(= local0 (- (ego x?) 3))
				else
					(= local0 (+ (ego x?) 4))
				)
				(aStars posn: local0 (- (ego y?) 19) show:)
				(= seconds 5)
			)
			(15
				(Print 22 22)
				(Print 22 23)
				(= currentStatus egoCAPTURED)
				(curRoom newRoom: 96)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (== state 3)
			(if (or (Said 'ok') (Said 'get/drink'))
				(if (< (ego y?) 162)
					(NotClose)
				else
					(Print 22 0)
					(self changeState: 4)
				)
			)
			(if (or (Said 'n') (Said 'barrel/drink'))
				(Print 22 1)
			)
			(if (Said 'call/man')
				(if (Random 0 1)
					(Print 22 2)
				else
					(Print 22 3)
				)
				(Print 22 4)
			)
		)
		(if (Said 'bang,open/door')
			(Print 22 5)
		)
		(if (Said 'look>')
			(if (Said '/man')
				(if (not henchmanIsHere)
					(Print 22 6)
				else
					(Print 22 7)
				)
			)
			(if (Said '/door')
				(Print 22 8)
			)
			(if (Said '/barrel,carpet,freeway')
				(Print 22 9)
			)
			(if (Said '/cup')
				(Print 22 10)
			)
			(if (Said '/lagoon,beach,fluid,lagoon')
				(Print 22 11)
			)
			(if (Said '/craft,boat')
				(Print 22 12)
			)
			(if (Said '/sign')
				(Print 22 13)
			)
			(if (Said '[/building,building,airport]')
				(Print 22 14)
				(Print 22 15)
			)
		)
		(if (Said '/door,bar')
			(Print 22 8)
		)
		(if (Said '/man>')
			(cond 
				((Said 'call/')
					(Print 22 16)
				)
				((Said '(stair<over),jerk,awaken/')
					(Print 22 17)
				)
			)
		)
	)
)

(instance boatScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds (Random 3 15))
			)
			(1
				(aBoat setMotion: MoveTo 333 107 self)
			)
			(2
				(aBoat hide:)
			)
		)
	)
)

(instance theSound of Sound
	(properties
		number 106
	)
)

(instance aView1 of PicView
	(properties
		y 76
		x 287
		view 252
		loop 1
		cel 1
		priority 4
	)
)

(instance aView2 of PicView
	(properties
		y 142
		x 64
		view 252
		loop 1
		cel 2
		priority 10
		signal ignrAct
	)
)

(instance aView3 of PicView
	(properties
		y 151
		x 188
		view 252
		loop 1
		cel 3
		priority 0
		signal ignrAct
	)
)

(instance aView4 of PicView
	(properties
		y 88
		x 284
		view 252
		loop 1
		priority 5
		signal ignrAct
	)
)

(instance aView5 of PicView
	(properties
		y 148
		x 63
		view 235
		priority 11
	)
)

(instance aView6 of PicView
	(properties
		y 156
		x 182
		view 235
		loop 1
		priority 11
	)
)

(instance aView7 of PicView
	(properties
		y 84
		x 54
		view 252
		loop 2
		priority 12
	)
)

(instance aSpudsSign1 of Prop
	(properties
		y 83
		x 32
		view 252
		loop 3
	)
)

(instance aSpudsSign2 of Prop
	(properties
		y 83
		x 77
		view 252
		loop 4
	)
)

(instance aSpudsSign3 of Prop
	(properties
		y 93
		x 54
		view 252
		loop 5
	)
)

(instance aBoat of Actor
	(properties
		y 106
		x 249
		view 252
		signal ignrAct
	)
)

(instance aHench of Actor
	(properties
		y 174
		x -11
		view 234
	)
)

(instance aStars of Prop
	(properties
		view 102
		loop 6
		signal ignrAct
	)
)
