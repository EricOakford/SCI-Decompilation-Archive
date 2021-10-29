;;; Sierra Script 1.0 - (do not remove this comment)
(script# 43)
(include sci.sh)
(use Main)
(use jet)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm43 0
)

(local
	marshall
	cockpitDoor
	bathroom
)
(instance rm43 of Room
	(properties
		picture 40
		style $0000
	)
	
	(method (init)
		(Load rsVIEW 0)
		(Load rsVIEW 82)
		(Load rsVIEW 26)
		(Load rsVIEW 25)
		(Load rsPIC 49)
		(super init:)
		(self setLocales: 154)
		(if (!= prevRoomNum 42) (ego posn: 91 145))
		(ego
			view: 0
			setStep: 3 2
			setLoop: -1
			setPri: -1
			ignoreActors:
			illegalBits: 0
			init:
		)
		((= keith (Actor new:))
			view: 20
			loop: 1
			posn: 272 1060
			ignoreActors:
			illegalBits: 0
			setCycle: Walk
			init:
		)
		((= cockpitDoor (Prop new:))
			view: 82
			posn: 271 55
			loop: 8
			cel: 0
			setPri: 0
			ignoreActors:
			cycleSpeed: 2
			init:
			stopUpd:
		)
		((= bathroom (Prop new:))
			view: 82
			posn: 51 191
			loop: 2
			cel: 0
			ignoreActors:
			setPri: 15
			addToPic:
		)
		((= marshall (Actor new:))
			view: 25
			posn: 317 1085
			ignoreActors:
			illegalBits: 0
			setCycle: Walk
			init:
		)
		((View new:)
			view: 84
			loop: 0
			cel: 3
			ignoreActors:
			posn: 263 75
			setPri: 1
			addToPic:
		)
		((View new:)
			view: 84
			loop: 1
			cel: 4
			ignoreActors:
			posn: 268 60
			setPri: 2
			addToPic:
		)
		(GoToBathroom)
		(HandsOff)
		(self setScript: StageFive)
	)
)

(instance StageFive of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 1))
			(1
				(AirplanePrint 43 0)
				(AirplanePrint 43 1)
				(ego setMotion: MoveTo 245 73 self)
			)
			(2
				(cockpitDoor setCycle: EndLoop self)
			)
			(3
				(keith posn: 270 55 setMotion: MoveTo 257 67 self)
			)
			(4
				(cockpitDoor setCycle: BegLoop)
				(AirplanePrint 43 2)
				(= seconds 2)
			)
			(5
				(AirplanePrint 43 3)
				(= seconds 3)
			)
			(6
				(keith setScript: keithActions)
				(= cycles 6)
			)
			(7
				(ego setMotion: MoveTo 229 59 self)
			)
			(8
				(ego
					view: 82
					posn: 212 72
					setLoop: 3
					setCel: 0
					cycleSpeed: 1
					setMotion: 0
					setPri: 3
					setCycle: EndLoop self
				)
			)
			(9 (= seconds 6))
			(10
				(AirplanePrint 43 4)
				(= seconds 3)
			)
			(11
				(AirplanePrint 43 5)
				(keith cue:)
				(ego setCycle: BegLoop self)
			)
			(12
				(ego
					view: 0
					loop: 0
					setPri: -1
					cycleSpeed: 0
					posn: 235 65
				)
				(marshall posn: 317 85 setMotion: MoveTo 281 70 self)
			)
			(13
				(marshall setMotion: MoveTo 263 65 self)
			)
			(14
				(AirplanePrint 43 6)
				(= seconds 2)
			)
			(15 (= seconds 2))
			(16
				(ego setMotion: MoveTo 245 65 self setCycle: Walk)
			)
			(17
				(AirplanePrint 43 7)
				(AirplanePrint 43 8)
				(PutInRoom 9)
				(PutInRoom 33)
				(AirplanePrint 43 9)
				(= seconds 1)
			)
			(18
				(ego setMotion: MoveTo 336 85 self setCycle: Walk)
				(keith cue:)
			)
			(19
				(ego hide:)
				(keith hide:)
				(curRoom drawPic: 49)
				(cast eachElementDo: #dispose)
				(RedrawCast)
				(if (Btst 94) (self changeState: 21) else (self cue:))
			)
			(20
				(Print 43 10)
				(Print 43 11)
				(Print 43 12)
				(EgoDead 43 13)
			)
			(21
				(Print 43 14)
				(Print 43 15)
				(curRoom newRoom: 100)
			)
		)
	)
)

(instance keithActions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 1))
			(1
				(keith setMotion: MoveTo 224 57 self)
			)
			(2
				(keith
					view: 82
					setPri: 0
					posn: 200 68
					setLoop: 4
					setCel: 0
					cycleSpeed: 1
					setCycle: EndLoop
				)
			)
			(3 (= seconds 1))
			(4 (keith setCycle: BegLoop self))
			(5
				(keith
					view: 20
					loop: 0
					setPri: -1
					cycleSpeed: 0
					posn: 223 57
				)
			)
			(6 (= cycles 4))
			(7
				(keith setMotion: MoveTo 323 88 setCycle: Walk)
			)
		)
	)
)
