;;; Sierra Script 1.0 - (do not remove this comment)
(script# 77)
(include sci.sh)
(use Main)
(use Intrface)
(use Extra)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm77 0
)

(local
	local0
)
(instance rm77 of Rm
	(properties
		picture 77
		horizon 50
		east 78
		south 75
		west 76
	)
	
	(method (init)
		(aSpit priority: (CoordPri 138))
		(super init:)
		(NormalEgo)
		(self setScript: rm77Script)
		(Load rsVIEW 708)
		(if global111 (= endGameState 105))
		(cond 
			((== endGameState 104)
				(= endGameState 105)
				(= currentStatus 22)
				(rm77Script changeState: 11)
				(Load rsVIEW 704)
				(addToPics add: aAsh doit:)
				(aKalalau illegalBits: 0 setCycle: Walk init:)
				(HandsOff)
			)
			((== endGameState 1)
				(= endGameState 2)
				(= currentStatus 11)
				(rm77Script changeState: 1)
				(Load rsVIEW 709)
				(aCampfire setLoop: 0 setPri: 10 setCycle: Fwd init:)
				(addToPics add: aWoman aSpit doit:)
				(aArm
					setLoop: 1
					setPri: (CoordPri 138)
					cycleSpeed: 5
					minPause: 2
					maxPause: 10
					isExtra: 1
					init:
				)
				(aWeaver setLoop: 7 cycleSpeed: 1 init:)
				(aBearer setLoop: 3 illegalBits: 0 setCycle: Walk init:)
				(aKalalau illegalBits: 0 setCycle: Walk init:)
				(HandsOff)
			)
			(else (addToPics add: aAsh2 doit:) (self setRegions: 700))
		)
		(cond 
			((== prevRoomNum 76) (ego posn: 3 143))
			((== prevRoomNum 78) (ego posn: 318 134))
			(else (ego loop: 3 posn: 165 187))
		)
		(ego init:)
	)
)

(instance rm77Script of Script
	(properties)
	
	(method (doit)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1 (= cycles 3))
			(2
				(aBearer setMotion: MoveTo 123 145 self)
				(= cycles 15)
			)
			(3 (Print 77 8))
			(4
				(aBearer setLoop: 4 cel: 0 setCycle: CT 7 1 self)
			)
			(5
				(addToPics add: aBasket doit:)
				(aBearer setCycle: End self)
			)
			(6
				(aBearer
					setLoop: 5
					setCycle: Walk
					setMotion: MoveTo -33 145 self
				)
				(Print 77 9 #draw)
			)
			(7 (Print 77 10) (= seconds 3))
			(8 (Print 77 11) (= seconds 3))
			(9
				(Print 77 12)
				(Print 77 13)
				(aKalalau setMotion: MoveTo 4 148 self)
				(ego setMotion: MoveTo 4 138)
			)
			(10 (curRoom newRoom: 76))
			(11 (= seconds 3))
			(12
				(Print 77 14)
				(Print 77 15)
				(aKalalau setMotion: MoveTo -5 132 self)
			)
			(13
				(Print 77 16)
				(Print 77 17 #at -1 130)
				(Print 77 18 #at 15 -1 #width 280)
				(Print 77 19 #at -1 130)
				(curRoom east: 0)
				(ego setMotion: MoveTo 345 124 self)
			)
			(14
				(Print 77 20 #at -1 20)
				(curRoom newRoom: 78)
			)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) evSAID) (event claimed?))
			(return)
		)
		(if (Said 'look>')
			(if (Said '/campfire,expectorate') (Print 77 0))
			(if (Said '/>')
				(if ((inventory at: 30) ownedBy: curRoomNum)
					(Print 77 1)
				else
					(Print 77 2)
				)
			)
			(if (Said '/door') (Print 77 3))
			(if (Said '[/airport,stair,angeles,hut]')
				(if ((inventory at: 30) ownedBy: curRoomNum)
					(Print 77 4)
				else
					(Print 77 5)
				)
			)
		)
		(if
			(or
				(Said '(board<in),(disembark<in),climb,board/stair,hut')
				(Said 'look/cup')
				(Said 'bang,open/door')
			)
			(Print 77 6)
		)
		(if (Said 'get/>')
			(cond 
				((!= currentStatus 0) (NotNow))
				((not ((inventory at: 30) ownedBy: curRoomNum)) (AlreadyTook))
				((not (ego inRect: 144 126 186 148)) (NotClose))
				(else (ego get: 30) (theGame changeScore: 6) (Print 77 7))
			)
		)
	)
)

(instance aAsh of PV
	(properties
		y 142
		x 165
		view 708
		loop 1
	)
)

(instance aWoman of PV
	(properties
		y 137
		x 124
		view 709
	)
)

(instance aSpit of PV
	(properties
		y 135
		x 160
		view 709
		loop 2
		signal $4000
	)
)

(instance aAsh2 of PV
	(properties
		y 142
		x 165
		view 708
		loop 1
	)
)

(instance aBasket of PV
	(properties
		y 144
		x 137
		view 709
		loop 6
		signal $4000
	)
)

(instance aKalalau of Act
	(properties
		y 183
		x 145
		view 704
		loop 3
		signal $4000
	)
)

(instance aCampfire of Prop
	(properties
		y 142
		x 165
		view 708
	)
)

(instance aArm of Extra
	(properties
		y 123
		x 118
		view 709
		signal $4000
	)
)

(instance aWeaver of Extra
	(properties
		y 107
		x 153
		view 709
	)
)

(instance aBearer of Act
	(properties
		y 144
		x 68
		view 709
		signal $4000
	)
)
