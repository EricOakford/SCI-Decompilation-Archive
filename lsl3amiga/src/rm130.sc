;;; Sierra Script 1.0 - (do not remove this comment)
(script# 130)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Menu)
(use Actor)
(use System)

(public
	rm130 0
)

(local
	local0
	local1
	local2
	housePosn = [
		158 73
		172 73
		185 73
		197 73
		205 65
		192 65
		178 65
		166 65
		151 66
		140 59
		150 59
		160 59
		171 59
		184 59
		196 59
		189 51
		180 51
		171 51
		162 51
		153 51
		999 999
		]
)
(procedure (localproc_08b2)
	(aHouse
		setPri: 1
		posn: [housePosn (++ local2)] [housePosn (++ local2)]
	)
	(AddViewToPic aHouse)
	(if (== 999 [housePosn local2]) (= local2 (- local2 2)))
)

(procedure (localproc_08e2)
	(aBuilding
		posn: (aBuilding x?) (+ 1000 (aBuilding y?))
	)
	(aWorkers posn: 999 999)
	((View new:)
		view: (aBuilding view?)
		setLoop: (aBuilding loop?)
		setCel: (aBuilding cel?)
		setPri: 3
		posn: (aBuilding x?) (- (- (aBuilding y?) 1000) local0)
		ignoreActors: TRUE
		addToPic:
	)
)

(procedure (localproc_096b)
	(aBuilding
		posn: (aBuilding x?) (- (aBuilding y?) local0)
	)
	(aWorkers
		posn: (aWorkers x?) (- (aWorkers y?) local0)
		cel: 0
		setCycle: EndLoop RoomScript
	)
)

(instance rm130 of Room
	(properties
		picture 130
		horizon 1
	)
	
	(method (init)
		(HandsOff)
		(Bset fSaveDisabled)
		(Bset fCursorHidden)
		(super init:)
		(self setScript: RoomScript)
		(theGame setSpeed: 6)
		(aBuilding init:)
		(aWorkers init:)
		(aHouse init:)
		(= local2 -1)
	)
)

(instance RoomScript of Script
	(method (doit)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds 3)
			)
			(1
				(Print 130 0 #at 10 5 #width 290 #time 12)
				(= seconds 3)
			)
			(2
				(Print 130 1 #at 10 5 #width 290 #time 16)
				(= seconds 3)
			)
			(3
				(Print 130 2 #at 10 5 #width 290 #time 22)
				(= seconds 3)
			)
			(4
				(Print 130 3 #at -1 120 #width 280 #dispose #time 9)
				(= seconds 7)
			)
			(5
				(= local0 16)
				(= local1 6)
				(aBuilding setCel: 0 posn: 22 117)
				(aWorkers
					setLoop: 1
					cel: 0
					posn: 22 117
					setCycle: EndLoop self
				)
			)
			(6
				(localproc_096b)
				(if (-- local1)
					(-- state)
				)
			)
			(7
				(localproc_08e2)
				(Print 130 4 #at -1 120 #width 280 #dispose #time 7)
				(= cycles 15)
			)
			(8
				(localproc_08b2)
				(= local0 13)
				(= local1 6)
				(aBuilding setCel: 1 posn: 55 116)
				(aWorkers
					setLoop: 2
					cel: 0
					posn: 55 116
					setCycle: EndLoop self
				)
			)
			(9
				(localproc_096b)
				(if (-- local1)
					(-- state)
				)
				(if (== local1 3)
					(localproc_08b2)
				)
			)
			(10
				(localproc_08b2)
				(localproc_08e2)
				(= cycles 15)
			)
			(11
				(localproc_08b2)
				(= local0 8)
				(= local1 6)
				(aBuilding setCel: 2 posn: 73 116)
				(aWorkers
					setLoop: 3
					cel: 0
					posn: 73 116
					setCycle: EndLoop self
				)
			)
			(12
				(localproc_096b)
				(if (-- local1)
					(-- state)
				)
				(if (== local1 3)
					(localproc_08b2)
				)
			)
			(13
				(localproc_08b2)
				(localproc_08e2)
				(= cycles 15)
			)
			(14
				(localproc_08b2)
				(= local0 11)
				(= local1 6)
				(aBuilding setCel: 3 posn: 96 114)
				(aWorkers
					setLoop: 4
					cel: 0
					posn: 96 114
					setCycle: EndLoop self
				)
			)
			(15
				(localproc_096b)
				(if (-- local1)
					(-- state)
				)
				(if (== local1 3)
					(localproc_08b2)
				)
			)
			(16
				(localproc_08b2)
				(localproc_08e2)
				(= cycles 15)
			)
			(17
				(localproc_08b2)
				(= local0 7)
				(= local1 5)
				(aBuilding setCel: 4 posn: 127 110)
				(aWorkers
					setLoop: 5
					cel: 0
					posn: 127 110
					setCycle: EndLoop self
				)
			)
			(18
				(localproc_096b)
				(if (-- local1)
					(-- state)
				)
				(if (== local1 3)
					(localproc_08b2)
				)
			)
			(19
				(localproc_08b2)
				(localproc_08e2)
				(Print 130 5 #at -1 120 #width 280 #dispose #time 9)
				(= cycles 15)
			)
			(20
				(localproc_08b2)
				(= local0 8)
				(= local1 6)
				(aBuilding setCel: 5 posn: 162 108)
				(aWorkers
					setLoop: 6
					cel: 0
					posn: 162 108
					setCycle: EndLoop self
				)
			)
			(21
				(localproc_096b)
				(if (-- local1) (-- state))
				(if (== local1 3) (localproc_08b2))
			)
			(22
				(localproc_08b2)
				(localproc_08e2)
				(= cycles 15)
			)
			(23
				(localproc_08b2)
				(= local0 8)
				(= local1 6)
				(aBuilding setCel: 5 posn: 193 108)
				(aWorkers
					setLoop: 6
					cel: 0
					posn: 193 108
					setCycle: EndLoop self
				)
			)
			(24
				(localproc_096b)
				(if (-- local1) (-- state))
			)
			(25
				(localproc_08e2)
				(= cycles 15)
			)
			(26
				(localproc_08b2)
				(= local0 8)
				(= local1 6)
				(aBuilding setCel: 5 posn: 224 108)
				(aWorkers
					setLoop: 6
					cel: 0
					posn: 224 108
					setCycle: EndLoop self
				)
			)
			(27
				(localproc_096b)
				(if (-- local1) (-- state))
			)
			(28
				(localproc_08b2)
				(localproc_08e2)
				(Print 130 6 #at -1 120 #width 280 #dispose #time 9)
				(= cycles 15)
			)
			(29
				(= local0 0)
				(aBuilding
					setLoop: 7
					cel: 0
					cycleSpeed: 1
					posn: 256 107
					setCycle: EndLoop self
				)
			)
			(30
				(localproc_08b2)
				(localproc_08e2)
				(= cycles 15)
			)
			(31
				(aBuilding
					setLoop: 8
					cel: 0
					posn: 286 107
					setCycle: EndLoop self
				)
			)
			(32
				(localproc_08b2)
				(localproc_08e2)
				(= cycles 15)
			)
			(33
				(aBuilding
					setLoop: 8
					cel: 0
					posn: 312 113
					setCycle: EndLoop self
				)
			)
			(34
				(localproc_08e2)
				(= cycles 15)
			)
			(35
				(Print 130 7 #at -1 120 #width 280 #time 12 #dispose)
				(= cycles 15)
			)
			(36
				(music fade:)
				(= seconds 6)
			)
			(37
				(cls)
				(curRoom newRoom: 120)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?))
			(
				(and
					(== (event type?) keyDown)
					(== (event message?) `#2)
				)
				(ToggleSound)
			)
			(
				(and
					modelessDialog
					(== (event message?) ENTER)
					(== (event type?) keyDown)
				)
				(event claimed: TRUE)
				(cls)
			)
			(else (curRoom newRoom: 140))
		)
	)
)

(instance aWorkers of Actor
	(properties
		y 1174
		x 23
		view 130
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self setLoop: 1 setPri: 4 ignoreActors: ignoreHorizon:)
	)
)

(instance aBuilding of Actor
	(properties
		y 999
		x 23
		view 130
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self
			setLoop: 0
			setCel: 0
			setPri: 3
			ignoreActors:
			ignoreHorizon:
		)
	)
)

(instance aHouse of Prop
	(properties
		y 999
		x 999
		view 130
		loop 9
	)
)
