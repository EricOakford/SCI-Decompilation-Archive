;;; Sierra Script 1.0 - (do not remove this comment)
(script# 74)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm74 0
)

(local
	vineInRoom
	local1
	local2
	swingingOnVine
	egoBigHead
	egoBigFace
	ripple
	egoSwinging1
	egoSwinging2
	egoSwinging3
	vine
)
(instance rm74 of Room
	(properties
		picture 74
		horizon 66
		east 75
		west 73
	)
	
	(method (init)
		(Load VIEW 178)
		(Load VIEW 729)
		(super init:)
		(if ((inventory at: iVine) ownedBy: curRoomNum)
			(= vineInRoom TRUE)
			((= vine (View new:))
				view: 178
				loop: 5
				posn: 184 37
				setPri: 9
				ignoreActors:
				stopUpd:
				init:
			)
		)
		((View new:)
			view: 178
			loop: 5
			cel: 1
			posn: 18 40
			setPri: 12
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 178
			loop: 5
			cel: 0
			posn: 37 55
			setPri: 12
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 178
			loop: 5
			cel: 0
			posn: 223 27
			setPri: 12
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 178
			loop: 5
			cel: 1
			posn: 202 10
			setPri: 12
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 178
			loop: 5
			cel: 1
			posn: 44 40
			setPri: 12
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 178
			loop: 5
			cel: 0
			posn: 192 32
			setPri: 12
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 178
			loop: 5
			posn: 23 51
			setPri: 7
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 178
			loop: 1
			posn: 11 42
			setPri: 7
			ignoreActors:
			addToPic:
		)
		((= ripple (Prop new:))
			view: 729
			setLoop: 0
			setCycle: Forward
			posn: 124 67
			init:
		)
		((= egoSwinging1 (Prop new:))
			view: 178
			ignoreActors:
			setLoop: 0
			setPri: 8
			posn: 85 44
			stopUpd:
			init:
		)
		((= egoSwinging2 (Prop new:))
			view: 178
			ignoreActors:
			setLoop: 1
			setPri: 8
			posn: 111 32
			stopUpd:
			init:
		)
		((= egoSwinging3 (Prop new:))
			view: 178
			ignoreActors:
			setLoop: 1
			setPri: 8
			posn: 136 20
			stopUpd:
			init:
		)
		(if (and (== passedPiranhaWater FALSE) (!= prevRoomNum 75))
			(Load VIEW 177)
			(Load VIEW 176)
			(Load VIEW 110)
			((= egoBigHead (View new:))
				view: 110
				ignoreActors:
				setPri: 14
				posn: 243 1098
				init:
			)
			((= egoBigFace (Prop new:))
				view: 177
				setLoop: 4
				ignoreActors:
				setPri: 15
				cycleSpeed: 1
				posn: 243 1098
				init:
			)
		)
		(NormalEgo)
		(if (== prevRoomNum 75)
			(ego posn: 288 78 observeControl: 2 16)
		else
			(ego posn: 2 76)
		)
		(ego init:)
		(self setRegions: 700 setScript: rm74Script)
	)
)

(instance rm74Script of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(cond 
			(
			(and (ego inRect: 261 72 321 77) (== currentStatus egoNormal)) (curRoom newRoom: 75))
			(
				(and
					(== currentStatus egoEatenByPiranha)
					(& (ego onControl:) $0010)
				)
				(self changeState: 3)
			)
			(
				(and
					(== currentStatus egoEatenByPiranha)
					(& (ego onControl:) $0400)
				)
				(if (== local2 FALSE) (= local2 TRUE) (Print 74 0))
			)
			(
				(and
					(== currentStatus egoEatenByPiranha)
					(& (ego onControl:) $2000)
				)
				(if (== local1 FALSE) (= local1 TRUE) (Print 74 1))
			)
			(
			(and (== currentStatus egoNormal) (== (ego onControl:) 2)) (self changeState: 1))
			(else (= local1 FALSE) (= local2 FALSE))
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(= currentStatus egoEatenByPiranha)
				(User canControl: TRUE canInput: TRUE)
				(ego
					view: 176
					observeControl: 1 -32768
					setLoop: -1
					setStep: 3 2
					setCycle: Walk
					setPri: -1
				)
				(= cycles 8)
			)
			(2 (Print 74 17 #draw))
			(3
				(Print 74 18)
				(ego
					view: 177
					setPri: -1
					illegalBits: -32768
					observeControl: 16 2 16384
				)
				(User canInput: FALSE)
				(= currentStatus egoStopped)
				(curRoom east: 0)
				(= cycles 0)
				(= seconds 6)
			)
			(4
				(User canControl: FALSE)
				(ego setMotion: 0 setLoop: 2)
				(egoBigHead posn: (ego x?) 87 stopUpd:)
				(egoBigFace posn: (ego x?) 87 cel: 0 setCycle: EndLoop self)
			)
			(5 (= cycles 7))
			(6
				(egoBigFace setCycle: BegLoop self)
			)
			(7
				(egoBigFace loop: 5 setCycle: EndLoop)
				(= seconds 3)
			)
			(8
				(Print 74 19 #at -1 20)
				(egoBigFace dispose:)
				(egoBigHead dispose:)
				(= seconds 3)
			)
			(9
				(Print 74 20)
				(Print 74 21)
				(= currentStatus egoDead)
			)
			(10
				(PrintOk)
				(ego hide:)
				(User canControl: FALSE)
				(= currentStatus egoOnVine1)
				(egoSwinging1 cel: 1 setCycle: CycleTo 7 1 self)
			)
			(11
				(if swingingOnVine
					(= swingingOnVine FALSE)
					(self changeState: 14)
				else
					(egoSwinging1 cel: 8 setCycle: EndLoop)
					(ego
						view: 178
						illegalBits: 0
						ignoreActors:
						ignoreHorizon:
						setLoop: 2
						setPri: 8
						setStep: 3 4
						cel: 0
						posn: 106 85
						show:
						setCycle: EndLoop
						setMotion: MoveTo 106 102 self
					)
				)
			)
			(12
				(ego setLoop: 4 posn: 106 103 cel: 0 setCycle: EndLoop self)
			)
			(13
				(Print 74 22 #at -1 20 #draw)
				(self changeState: 1)
			)
			(14
				(egoSwinging1 setCel: 8 setCycle: EndLoop)
				(egoSwinging2 setCel: 1 setCycle: CycleTo 6 1 self)
			)
			(15
				(if swingingOnVine
					(= swingingOnVine FALSE)
					(self changeState: 18)
				else
					(egoSwinging2 cel: 7 setCycle: EndLoop)
					(ego
						view: 178
						illegalBits: 0
						ignoreActors:
						ignoreHorizon:
						setLoop: 2
						setPri: 8
						setStep: 3 7
						cel: 0
						posn: 131 73
						show:
						setCycle: EndLoop
						setMotion: MoveTo 131 102 self
					)
				)
			)
			(16
				(ego setLoop: 4 posn: 131 103 cel: 0 setCycle: EndLoop self)
			)
			(17
				(Print 74 23 #at -1 20 #draw)
				(self changeState: 1)
			)
			(18
				(egoSwinging1 stopUpd:)
				(egoSwinging2 setCel: 7 setCycle: EndLoop)
				(egoSwinging3 cel: 1 setCycle: CycleTo 6 1 self)
			)
			(19
				(egoSwinging2 stopUpd:)
				(if swingingOnVine
					(= swingingOnVine FALSE)
					(egoSwinging3 setCel: 6)
					(self changeState: 23)
				else
					(egoSwinging3 setCycle: BegLoop self)
				)
			)
			(20
				(ego
					view: 178
					illegalBits: 0
					ignoreActors:
					ignoreHorizon:
					setLoop: 2
					setPri: 8
					setStep: 3 7
					cel: 0
					posn: 131 73
					show:
					setCycle: EndLoop
					setMotion: MoveTo 131 102 self
				)
			)
			(21
				(ego setLoop: 4 posn: 131 103 cel: 0 setCycle: EndLoop self)
			)
			(22
				(Print 74 24 #at -1 20 #draw)
				(self changeState: 1)
			)
			(23
				(egoSwinging3 setCel: 7 setCycle: EndLoop)
				(ego
					illegalBits: 0
					ignoreActors:
					ignoreHorizon:
					view: 178
					setLoop: 3
					setPri: 9
					setCel: 0
					posn: 158 61
					show:
					setCycle: CycleTo 5 1
					setStep: 1 10
					setMotion: MoveTo 167 122 self
				)
			)
			(24
				(egoSwinging3 stopUpd:)
				(ego setCycle: EndLoop self)
			)
			(25
				(NormalEgo 0)
				(if (== passedPiranhaWater FALSE)
					(= passedPiranhaWater TRUE)
					(theGame changeScore: 6)
					(Print 74 25 #at -1 152 #draw)
				)
			)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look>')
			(if (Said '/lagoon,beach') (Print 74 2))
			(if (Said '/brook,fluid') (Print 74 3))
			(if (Said '/fish')
				(if (!= currentStatus egoEatenByPiranha)
					(Print 74 4)
				else
					(Print 74 5)
				)
			)
			(if (Said '/palm,landscape,bush')
				(Print 74 6)
				(if (== vineInRoom TRUE) (Print 74 7))
			)
			(if (Said '/boulder,cascade') (Print 74 8))
			(if (Said '[/airport,forest]') (Print 74 9))
		)
		(if (Said 'jerk,get/landscape')
			(cond 
				((!= currentStatus egoNormal) (PrintNotNow))
				((not vineInRoom) (Print 74 10))
				((ego inRect: 59 91 78 98) (Print 74 11))
				((not (ego inRect: 149 100 195 129)) (PrintNotCloseEnough))
				(else
					(= vineInRoom FALSE)
					(ego get: iVine)
					(theGame changeScore: 4)
					(vine dispose:)
					(Print 74 12 #draw)
					(Print 74 13)
				)
			)
		)
		(if (Said 'bathing') (Print 74 14))
		(if (Said '*/boulder')
			(if (== currentStatus egoEatenByPiranha)
				(Print 74 15)
			else
				(Print 74 16)
			)
		)
		(if
			(or
				(Said 'hop,(let<board)')
				(Said 'free,carry,apply,swing,alter/landscape')
				(Said 'swing/brook,fluid')
				(Said 'swing<on/landscape')
			)
			(cond 
				(
				(and (>= currentStatus egoOnVine1) (<= currentStatus egoOnVine3)) (= swingingOnVine TRUE))
				((!= currentStatus egoNormal) (PrintNotNow))
				((not (ego inRect: 59 91 78 98)) (PrintNotCloseEnough))
				(else (self changeState: 10))
			)
		)
	)
)
