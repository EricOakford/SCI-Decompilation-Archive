;;; Sierra Script 1.0 - (do not remove this comment)
(script# rNonookeeThrone)
(include game.sh)
(use Main)
(use Intrface)
(use Extra)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm93 0
)

(local
	local0
	aGenerator
	aRadio
	aRightPanel1
	aRightPanel2
	aLeftPanel1
	aLeftPanel2
	aDoor
	aFanEast
	aFanWest
	aGrapeEast
	aGrapeWest
)
(instance theSound of Sound
	(properties
		number sNonookee
		priority 30
		loop -1
	)
)

(instance rm93 of Room
	(properties
		picture rNonookeeThrone
		style IRISOUT
	)
	
	(method (init)
		(Load VIEW vThroneStuff)
		(Load VIEW vNonookee)
		(Load VIEW vFanGirl)
		(Load VIEW vGrapeGirl)
		(Load SOUND sNonookee)
		(super init:)
		(theSound play:)
		((View new:)
			view: vThroneStuff
			loop: 5
			cel: 0
			posn: 39 85
			addToPic:
		)
		((View new:)
			view: vThroneStuff
			ignoreActors:
			loop: 5
			cel: 1
			posn: 251 107
			setPri: 10
			addToPic:
		)
		((View new:)
			view: vThroneStuff
			loop: 5
			cel: 2
			posn: 291 107
			setPri: 10
			addToPic:
		)
		((View new:)
			view: vThroneStuff
			loop: 5
			cel: 3
			posn: 253 73
			setPri: 5
			addToPic:
		)
		((View new:)
			view: vThroneStuff
			ignoreActors:
			loop: 5
			cel: 4
			posn: 23 139
			setPri: 15
			addToPic:
		)
		((View new:)
			view: vThroneStuff
			ignoreActors:
			loop: 5
			cel: 5
			posn: 294 139
			setPri: 15
			addToPic:
		)
		((= aGenerator (Extra new:))
			view: vThroneStuff
			setLoop: 0
			posn: 258 101
			setPri: 7
			init:
			cycleSpeed: 0
			setCel: 0
		)
		((= aRadio (Extra new:))
			view: vThroneStuff
			setLoop: 1
			posn: 263 75
			setPri: 4
			init:
			cycleSpeed: 0
		)
		((= aRightPanel1 (Extra new:))
			view: vThroneStuff
			ignoreActors:
			setLoop: 3
			posn: 267 163
			setPri: 15
			pauseCel: -1
			init:
			cycleSpeed: 0
		)
		((= aRightPanel2 (Extra new:))
			view: vThroneStuff
			ignoreActors:
			loop: 3
			posn: 283 151
			setPri: 15
			pauseCel: -1
			init:
			cycleSpeed: 1
		)
		((= aLeftPanel1 (Extra new:))
			view: vThroneStuff
			ignoreActors:
			loop: 2
			cel: 1
			posn: 35 151
			setPri: 15
			pauseCel: -1
			init:
			cycleSpeed: 1
		)
		((= aLeftPanel2 (Extra new:))
			view: vThroneStuff
			ignoreActors:
			setLoop: 2
			posn: 50 163
			setPri: 15
			pauseCel: -1
			init:
			cycleSpeed: 1
		)
		((= aDoor (Prop new:))
			view: vThroneStuff
			loop: 4
			setCel: 255
			setPri: 5
			posn: 96 84
			stopUpd:
			init:
		)
		((= aGrapeWest (Prop new:))
			view: vGrapeGirl
			ignoreActors:
			setLoop: 0
			setCycle: Forward
			setPri: 10
			posn: 142 98
			cycleSpeed: 1
			stopUpd:
			init:
		)
		((= aGrapeEast (Prop new:))
			view: vGrapeGirl
			ignoreActors:
			setLoop: 1
			setCycle: Forward
			setPri: 10
			posn: 179 98
			cycleSpeed: 1
			stopUpd:
			init:
		)
		((= aFanWest (Prop new:))
			view: vFanGirl
			ignoreActors:
			setLoop: 0
			posn: 132 85
			setCycle: Forward
			cycleSpeed: 2
			stopUpd:
			init:
		)
		((= aFanEast (Prop new:))
			view: vFanGirl
			ignoreActors:
			setLoop: 1
			posn: 191 85
			setCycle: Forward
			cycleSpeed: 3
			stopUpd:
			init:
		)
		(NormalEgo loopS)
		(ego view: vNonookee posn: 84 82 init:)
		(self setScript: rm93Script)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(event claimed: TRUE)
		(return
			(if
				(and
					(== (event type?) keyDown)
					(== (event message?) `#2)
				)
				(event claimed: TRUE)
				(DoSound SoundOn (not (DoSound SoundOn)))
			else
				0
			)
		)
	)
)

(instance rm93Script of Script

	(method (doit)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 86 127 self)
				(= cycles 10)
			)
			(1
				(aDoor setCycle: BegLoop)
			)
			(2
				(aDoor stopUpd:)
				(ego setMotion: MoveTo 66 160 self)
			)
			(3
				(Print 93 0
					#at -1 15
					#width 280
					#time 10
				)
				(= cycles 30)
			)
			(4
				(ego setMotion: MoveTo 249 160 self)
			)
			(5
				(Print 93 1
					#at -1 15
					#width 280
					#time 5
				)
				(Print 93 2
					#at -1 20
					#time 5
				)
				(= cycles 30)
			)
			(6
				(ego setMotion: MoveTo 225 160 self)
			)
			(7
				(ego setMotion: MoveTo 225 97 self)
			)
			(8
				(ego setMotion: MoveTo 241 93 self)
			)
			(9
				(Print 93 3 #at -1 20 #time 5)
				(Print 93 4 #at -1 20 #time 4)
				(Print 93 5 #at -1 15 #width 280 #time 5)
				(Print 93 6 #at -1 20 #time 4)
				(Print 93 7 #at -1 20 #time 4)
				(Print 93 8 #at -1 15 #width 280 #time 10)
				(= cycles 30)
			)
			(10
				(ego setMotion: MoveTo 219 99 self)
			)
			(11
				(ego setMotion: MoveTo 219 135 self)
			)
			(12
				(ego setMotion: MoveTo 161 135 self)
			)
			(13
				(ego setMotion: MoveTo 161 98 self)
			)
			(14
				(Print 93 9 #at -1 15 #width 280 #time 8)
				(= cycles 6)
			)
			(15
				(ego
					illegalBits: 0
					ignoreActors:
					setLoop: 4
					posn: 160 100
					cycleSpeed: 2
					cel: 0
					setCycle: EndLoop self
				)
			)
			(16
				(Print 93 10 #at -1 20 #time 4 #draw)
				((View new:)
					view: vGrapeGirl
					ignoreActors:
					loop: 4
					posn: 161 96
					setPri: 6
					addToPic:
				)
				(ego
					view: vGrapeGirl
					setLoop: 2
					setPri: 8
					posn: 161 71
					cycleSpeed: 1
				)
				(aFanEast setCycle: Forward)
				(aFanWest setCycle: Forward)
				(= cycles 30)
			)
			(17
				(Print 93 11 #at -1 15 #width 280 #time 5)
				(= cycles 10)
			)
			(18
				(aGrapeEast setCycle: CycleTo 4 1 self)
			)
			(19
				(ego setCycle: EndLoop)
				(aGrapeEast setCycle: EndLoop self)
			)
			(20
				(ego setCycle: BegLoop)
				(aGrapeEast setCycle: BegLoop self)
			)
			(21 (= cycles 10))
			(22
				(aGrapeWest setCycle: CycleTo 4 1 self)
			)
			(23
				(ego setLoop: 3 setCycle: EndLoop)
				(aGrapeWest setCycle: EndLoop self)
			)
			(24
				(ego setCycle: BegLoop)
				(aGrapeWest setCycle: BegLoop self)
			)
			(25 (= cycles 30))
			(26
				(Print 93 12 #at -1 15 #width 280 #time 6)
				(curRoom newRoom: TITLE)
			)
		)
	)
)
