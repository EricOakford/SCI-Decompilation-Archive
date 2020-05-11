;;; Sierra Script 1.0 - (do not remove this comment)
(script# 22)
(include game.sh)
(use Main)
(use Intrface)
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
	scurvyNeon
	dogNeon
	saloonNeon
	boat
	henchman
	dizzy
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
		((View new:)
			view: 252
			loop: 1
			cel: 1
			posn: 287 76
			setPri: 4
			addToPic:
		)
		((View new:)
			view: 252
			loop: 1
			cel: 2
			posn: 64 142
			setPri: 10
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 252
			loop: 1
			cel: 3
			posn: 188 151
			setPri: 0
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 252
			loop: 1
			posn: 284 88
			setPri: 5
			ignoreActors:
			addToPic:
		)
		((View new:) view: 235 posn: 63 148 setPri: 11 addToPic:)
		((View new:)
			view: 235
			loop: 1
			posn: 182 156
			setPri: 11
			addToPic:
		)
		((View new:)
			view: 252
			loop: 2
			posn: 54 84
			setPri: 12
			addToPic:
		)
		((= scurvyNeon (Prop new:))
			view: 252
			setLoop: 3
			setPri: 12
			posn: 32 83
			cycleSpeed: 4
			setCycle: Forward
			init:
		)
		((= dogNeon (Prop new:))
			view: 252
			setLoop: 4
			setPri: 12
			posn: 77 83
			cycleSpeed: 4
			setCycle: Forward
			init:
		)
		((= saloonNeon (Prop new:))
			view: 252
			setLoop: 5
			setPri: 12
			posn: 54 93
			cycleSpeed: 4
			setCycle: Forward
			init:
		)
		((= boat (Actor new:))
			view: 252
			setLoop: 0
			setPri: 7
			illegalBits: 0
			ignoreActors:
			ignoreHorizon:
			posn: 249 106
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
			((= henchman (Actor new:))
				view: 234
				posn: -11 174
				illegalBits: -32768
				setCycle: Walk
				init:
				hide:
			)
			((= dizzy (Prop new:))
				view: 102
				setLoop: 6
				setCycle: Forward
				setPri: 13
				ignoreActors:
				init:
				hide:
			)
		)
		(cond 
			((== prevRoomNum 0) (ego posn: 302 187))
			((== prevRoomNum 18) (ego posn: 258 128))
			((== prevRoomNum 26) (ego posn: 302 187))
		)
		(NormalEgo)
		(ego init:)
	)
)

(instance rm22Script of Script
	(properties)
	
	(method (doit)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1 (= seconds (Random 3 5)))
			(2
				(henchman show: setMotion: MoveTo 127 175 self)
				(theSound play:)
			)
			(3
				(henchman
					setLoop: (if (< (henchman x?) (ego x?)) 0 else 1)
				)
				(Print 22 18 #draw)
			)
			(4
				(HandsOff)
				(= currentStatus egoStopped)
				(if (< (henchman x?) (ego x?))
					(ego
						setAvoider: Avoider
						ignoreActors:
						setMotion: MoveTo (+ (henchman x?) 20) (+ 1 (henchman y?)) self
					)
				else
					(ego
						setAvoider: Avoider
						ignoreActors:
						setMotion: MoveTo (- (henchman x?) 20) (+ 1 (henchman y?)) self
					)
				)
			)
			(5
				(ego
					view: 102
					setLoop: (if (< (henchman x?) (ego x?)) 1 else 0)
					cel: 0
					setCycle: EndLoop self
				)
				(= local0 (if (< (henchman x?) (ego x?)) 0 else 1))
				(henchman setLoop: (+ 2 local0) setCycle: EndLoop)
			)
			(6
				(henchman stopUpd:)
				(ego
					setLoop: (+ 2 (ego loop?))
					cel: 0
					setCycle: EndLoop self
				)
			)
			(7 (= seconds 3))
			(8 (Print 22 19) (= seconds 3))
			(9 (ego setCycle: CycleTo 3 -1 self))
			(10 (ego setCycle: EndLoop self))
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
				(dizzy posn: local0 (- (ego y?) 19) show:)
				(= seconds 5)
			)
			(15
				(Print 22 22)
				(Print 22 23)
				(= currentStatus egoCaptured)
				(curRoom newRoom: 96)
			)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (== state 3)
			(if (or (Said 'ok') (Said 'get/drink'))
				(if (< (ego y?) 162)
					(PrintNotCloseEnough)
				else
					(Print 22 0)
					(self changeState: 4)
				)
			)
			(if (or (Said 'n') (Said 'barrel/drink'))
				(Print 22 1)
			)
			(if (Said 'call/man')
				(if (Random 0 1) (Print 22 2) else (Print 22 3))
				(Print 22 4)
			)
		)
		(if (Said 'bang,open/door') (Print 22 5))
		(if (Said 'look>')
			(if (Said '/man')
				(if (not henchmanIsHere) (Print 22 6) else (Print 22 7))
			)
			(if (Said '/door') (Print 22 8))
			(if (Said '/barrel,carpet,freeway') (Print 22 9))
			(if (Said '/cup') (Print 22 10))
			(if (Said '/lagoon,beach,fluid,lagoon') (Print 22 11))
			(if (Said '/craft,boat') (Print 22 12))
			(if (Said '/sign') (Print 22 13))
			(if (Said '[/building,building,airport]')
				(Print 22 14)
				(Print 22 15)
			)
		)
		(if (Said '/door,bar') (Print 22 8))
		(if (Said '/man>')
			(cond 
				((Said 'call/') (Print 22 16))
				((Said '(stair<over),jerk,awaken/') (Print 22 17))
			)
		)
	)
)

(instance boatScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 3 15)))
			(1
				(boat setMotion: MoveTo 333 107 self)
			)
			(2 (boat hide:))
		)
	)
)

(instance theSound of Sound
	(properties
		number 106
	)
)
