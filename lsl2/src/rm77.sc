;;; Sierra Script 1.0 - (do not remove this comment)
(script# 77)
(include game.sh)
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
	aCampfire
	aArm
	aKalalau
	aBearer
	aWeaver
)
(instance rm77 of Room
	(properties
		picture 77
		horizon 50
		east 78
		south 75
		west 76
	)
	
	(method (init)
		(super init:)
		(NormalEgo)
		(self setScript: rm77Script)
		(Load VIEW 708)
		(if global111 (= endGameState 105))
		(cond 
			((== endGameState 104)
				(= endGameState 105)
				(= currentStatus egoWONGAME)
				(rm77Script changeState: 11)
				(Load VIEW 704)
				((View new:)
					view: 708
					setLoop: 1
					posn: 165 142
					addToPic:
				)
				((= aKalalau (Actor new:))
					view: 704
					ignoreActors:
					illegalBits: 0
					loop: 3
					posn: 145 183
					setCycle: Walk
					init:
				)
				(HandsOff)
			)
			((== endGameState endMEETKALALAU)
				(= endGameState endMEETTRIBE)
				(= currentStatus egoMEETTRIBE)
				(rm77Script changeState: 1)
				(Load VIEW 709)
				((= aCampfire (Prop new:))
					view: 708
					setLoop: 0
					setPri: 10
					posn: 165 142
					setCycle: Forward
					init:
				)
				((View new:) view: 709 loop: 0 posn: 124 137 addToPic:)
				((View new:)
					view: 709
					ignoreActors:
					loop: 2
					posn: 160 135
					setPri: (CoordPri 138)
					addToPic:
				)
				((= aArm (Extra new:))
					view: 709
					ignoreActors:
					loop: 1
					posn: 118 123
					setPri: (CoordPri 138)
					cycleSpeed: 5
					minPause: 2
					maxPause: 10
					isExtra: 1
					init:
				)
				((= aWeaver (Extra new:))
					view: 709
					loop: 7
					posn: 153 107
					cycleSpeed: 1
					init:
				)
				((= aBearer (Actor new:))
					view: 709
					ignoreActors:
					illegalBits: 0
					setLoop: 3
					setCycle: Walk
					posn: 68 144
					init:
				)
				((= aKalalau (Actor new:))
					view: 704
					ignoreActors:
					illegalBits: 0
					loop: 3
					posn: 145 183
					setCycle: Walk
					init:
				)
				(HandsOff)
			)
			(else
				((View new:)
					view: 708
					setLoop: 1
					posn: 165 142
					addToPic:
				)
				(self setRegions: ISLAND)
			)
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
				(aBearer setLoop: 4 cel: 0 setCycle: CycleTo 7 1 self)
			)
			(5
				((View new:)
					view: 709
					ignoreActors:
					loop: 6
					posn: 137 144
					addToPic:
				)
				(aBearer setCycle: EndLoop self)
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
				(Print 77 17 #at -1 152)
				(Print 77 18 #at 15 -1 #width 280)
				(Print 77 19 #at -1 152)
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
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look>')
			(if (Said '/campfire,expectorate') (Print 77 0))
			(if (Said '/>')
				(if ((inventory at: iAshes) ownedBy: curRoomNum)
					(Print 77 1)
				else
					(Print 77 2)
				)
			)
			(if (Said '/door') (Print 77 3))
			(if (Said '[/airport,stair,angeles,hut]')
				(if ((inventory at: iAshes) ownedBy: curRoomNum)
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
				((!= currentStatus egoNORMAL) (NotNow))
				((not ((inventory at: iAshes) ownedBy: curRoomNum)) (AlreadyTook))
				((not (ego inRect: 144 126 186 148)) (NotClose))
				(else (ego get: iAshes) (theGame changeScore: 6) (Print 77 7))
			)
		)
	)
)
