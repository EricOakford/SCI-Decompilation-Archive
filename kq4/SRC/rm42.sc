;;; Sierra Script 1.0 - (do not remove this comment)
(script# 42)
(include game.sh)
(use Main)
(use Intrface)
(use Avoider)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room42 0
)
(synonyms
	(room cottage)
	(shelf cabinet)
	(fisherman man)
	(woman woman)
)

(local
	fishingPole
	[local1 3]
	wife
	wifeSitting
	fisherman
	fishermanSitting
	newProp
	wifeAtCounter
)
(instance Room42 of Room
	(properties
		picture 42
		style (| BLACKOUT IRISOUT) ;$0010
	)
	
	(method (init)
		(Load VIEW 513)
		(if (ego has: iDiamondPouch)
			(Load VIEW 243)
			(Load VIEW 242)
			(Load VIEW 240)
			(Load VIEW 241)
		)
		(super init:)
		(= isIndoors TRUE)
		(if (ego has: iDiamondPouch) (= wifeAtCounter TRUE))
		(ego
			view: 4
			loop: 0
			setStep: 4 2
			illegalBits: cWHITE
			setPri: -1
			posn: 63 125
			init:
		)
		((= newProp (Prop new:))
			view: 513
			posn: 133 84
			setLoop: 2
			ignoreActors: 1
			init:
			cycleSpeed: 1
			setCycle: Forward
		)
		(if (ego has: iDiamondPouch)
			((View new:)
				view: 513
				loop: 0
				cel: 2
				posn: 181 93
				addToPic:
			)
			((= wife (Actor new:))
				view: 241
				loop: 0
				setCycle: Forward
				x: 200
				y: 117
				illegalBits: 0
				init:
				setScript: doBread
			)
			((View new:)
				view: 513
				loop: 3
				cel: 0
				posn: 82 154
				addToPic:
			)
		else
			(if
				(and
					(== fishermanState fisherAtHome)
					(!= ((inventory at: iDiamondPouch) owner?) 42)
				)
				((= wife (Actor new:))
					view: 240
					illegalBits: 0
					loop: 2
					posn: 84 132
					ignoreActors: TRUE
					init:
					setPri: 12
					setScript: wifeTalk
				)
			else
				((= wife (Actor new:))
					view: 240
					loop: 3
					illegalBits: 0
					posn: 84 132
					ignoreActors: TRUE
					init:
					setPri: 12
					setScript: wifeTalk
				)
			)
			((= wifeSitting (Actor new:))
				view: 240
				loop: 4
				cel: 1
				illegalBits: 0
				posn: 84 153
				init:
				setPri: 12
			)
			(chairBlock
				top: (- (wifeSitting y?) 5)
				bottom: (+ (wifeSitting y?) 5)
				left: (- (wifeSitting x?) 13)
				right: (+ (wifeSitting x?) 15)
				init:
			)
			(ego observeBlocks: chairBlock)
		)
		(if (== fishermanState fisherAtHome)
			((View new:)
				view: 513
				loop: 0
				cel: 2
				posn: 181 93
				addToPic:
			)
			((= fisherman (Actor new:))
				view: 235
				loop: 0
				cel: 0
				ignoreActors: TRUE
				illegalBits: 0
				posn: 130 135
				init:
				setPri: 12
				setScript: fishermanSit
			)
			((= fishermanSitting (View new:))
				view: 235
				loop: 2
				cel: 0
				posn: 129 157
				init:
				stopUpd:
				setPri: 12
			)
			(if (== ((inventory at: iFishingPole) owner?) 204)
				((= fishingPole (Actor new:))
					view: 513
					loop: 1
					cel: 0
					illegalBits: 0
					posn: 223 115
					init:
					stopUpd:
					ignoreActors: 1
				)
			)
		else
			((View new:)
				view: 513
				loop: 3
				cel: 1
				posn: 138 161
				addToPic:
			)
		)
	)
	
	(method (doit)
		(if (& (ego onControl: 0) cBROWN)
			(ego loop: 1)
			(Animate (cast elements?) FALSE)
			(curRoom newRoom: 7)
		)
		(super doit:)
	)
	
	(method (handleEvent event &tmp inventorySaidMe)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said 'look[<around][/room,room,place]')
						(if wifeAtCounter
							(Print
								(Format @str 42 0
									(cond 
										((not (cast contains: fisherman))
											{You see a tired-looking woman kneading bread at the counter.}
										)
										((not (ego has: iFishingPole))
											{You see the old fisherman at the table, and his wife kneading bread at the counter. The fisherman's fishing pole has been set in the corner.}
										)
										((not (ego has: iDiamondPouch))
											{You see the old fisherman at the table, and his wife kneading bread at the counter. Since your generous gift of the diamonds, however, her grouchy look has been replaced by one of contentment.}
										)
										(else {_})
									)
								)
							)
						else
							(Print
								(Format @str 42 0
									(cond 
										((not (cast contains: fisherman)) {You see a tired-looking woman knitting at the table.})
										((not (ego has: iFishingPole))
											{The fisherman's worn and tattered wife sits at the table with her husband, talking. The fisherman's pole has been set into the corner.}
										)
										((not (ego has: iDiamondPouch))
											{The fisherman's worn and tattered wife sits at the table with her husband, talking. Since your generous gift of the diamonds, however, her grouchy look has been replaced by one of contentment.}
										)
										(else {_})
									)
								)
							)
						)
					)
					(
					(and (cast contains: fisherman) (Said '/person')) (Print 42 1))
					((Said '/people')
						(if (cast contains: fisherman)
							(Print 42 1)
						else
							(Print 42 2)
						)
					)
					((Said 'look>')
						(cond 
							((Said '/woman,person')
								(cond 
									(wifeAtCounter
										(cond 
											((not (ego has: iFishingPole)) (Print 42 3))
											((not (ego has: iDiamondPouch)) (Print 42 4))
										)
									)
									((!= fishermanState fisherAtHome) (Print 42 5))
									((== ((inventory at: iDiamondPouch) owner?) 42) (Print 42 6))
									(else (Print 42 7))
								)
							)
							((Said '/dough[<bread]') (Print 42 8))
							((Said '<under/table') (Print 42 9))
							((Said '/table') (Print 42 10))
							((Said '/chair') (Print 42 11))
							((Said '<under/bed') (Print 42 12))
							((Said '/bed') (Print 42 13))
							((Said '/stove') (Print 42 14))
							((Said '/window')
								(cond 
									(
										(or
											(ego inRect: 14 127 52 140)
											(ego inRect: 66 110 88 119)
										)
										(Print 42 15)
									)
									((ego inRect: 236 118 261 139) (Print 42 16))
									(else (Print 800 1))
								)
							)
							((Said '/door') (Print 42 17))
							((Said '/wall') (Print 42 18))
							((or (Said '/dirt') (Said '<down')) (Print 42 19))
							((Said '/pole')
								(cond 
									(
										(and
											(== fishermanState fisherAtHome)
											(== ((inventory at: iFishingPole) owner?) 204)
										)
										(Print 42 20)
									)
									((ego has: iFishingPole) (event claimed: FALSE))
									(else (Print 42 21))
								)
							)
							((Said '/fisherman')
								(cond 
									((!= fishermanState fisherAtHome) (Print 42 22))
									((not wifeAtCounter) (Print 42 23))
									((== ((inventory at: iDiamondPouch) owner?) 42) (Print 42 24))
									(else (Print 42 25))
								)
							)
							((Said '/caldron,kettle,coffee') (Print 42 26))
							((Said '/fish')
								(if (not (ego has: iFish))
									(Print 42 27)
								else
									(event claimed: FALSE)
								)
							)
							((Said '/shelf') (Print 42 28))
							((Said '/can') (Print 42 29))
							((Said '/knitting,knitting')
								(if
								(and (== (wife view?) 240) (== (wife loop?) 3))
									(Print 42 30)
								else
									(Print 42 31)
								)
							)
						)
					)
					((Said 'eat/dough') (Print 42 32))
					((Said 'rob/pole')
						(cond 
							(
								(and
									(== ((inventory at: iFishingPole) owner?) 204)
									(== fishermanState fisherAtHome)
								)
								(Print 42 33)
							)
							(((inventory at: iFishingPole) ownedBy: ego) (Print 800 0))
							(else (Print 42 21))
						)
					)
					((Said 'kiss>')
						(cond 
							((Said '[/!*]')
								(if (== fishermanState fisherAtHome)
									(Print 42 34)
								else
									(Print 42 35)
								)
							)
							((Said '/woman,person') (Print 42 35))
							((Said '/fisherman')
								(if (== fishermanState fisherAtHome)
									(Print 42 36)
								else
									(Print 42 37)
								)
							)
						)
					)
					((Said 'help') (Print 42 38))
					(
						(or
							(Said 'converse/woman,person')
							(and
								(< (ego distanceTo: wife) 50)
								(Said 'converse[/!*]')
							)
						)
						(cond 
							((== ((inventory at: iDiamondPouch) owner?) 42) (wifeTacoDoco2 cue:))
							((cast contains: fisherman) (wifeTacoDoco1 cue:))
							(else (wifeTacoDoco cue:))
						)
					)
					(
						(or
							(Said 'converse/fisherman')
							(and
								(cast contains: fisherman)
								(< (ego distanceTo: fisherman) 50)
								(Said 'converse[/!*]')
							)
						)
						(if (== fishermanState 3)
							(if (== ((inventory at: 1) owner?) 42)
								(fishermanTacoDoco1 cue:)
							else
								(fishermanTacoDoco2 cue:)
							)
						else
							(Print 42 39)
						)
					)
					((Said 'converse[/!*]')
						(cond 
							((cast contains: fisherman) (Print 42 40))
							((== ((inventory at: iDiamondPouch) owner?) 42) (wifeTacoDoco2 cue:))
							((cast contains: fisherman) (wifeTacoDoco1 cue:))
							(else (wifeTacoDoco cue:))
						)
					)
					((Said 'close,open/door') (Print 42 41))
					((Said 'sit') (Print 42 42))
					(
						(or
							(Said 'lay,sleep[<down,on,in]')
							(Said 'get<in,on,in/bed')
						)
						(Print 42 13)
					)
					((Said 'drink,get/coffee,cup') (Print 42 43))
					((Said 'deliver>')
						(if
							(or
								(< (ego distanceTo: wife) 20)
								(and
									(cast contains: fisherman)
									(< (ego distanceTo: fishermanSitting) 30)
								)
							)
							(if
								(and
									(= inventorySaidMe (inventory saidMe:))
									(ego has: (inventory indexOf: inventorySaidMe))
								)
								(cond 
									(
										(and
											(== (inventory indexOf: inventorySaidMe) 1)
											(== fishermanState fisherAtHome)
										)
										(if (< (ego distanceTo: wife) 25)
											(Print 42 44)
										else
											(Print 42 45)
										)
										(ego put: iDiamondPouch 42)
										(wife setScript: givePole)
									)
									((== fishermanState fisherAtHome) (Print 42 46))
									(else (Print 42 47))
								)
							else
								(if inventorySaidMe (Print 800 2) else (Print 42 48))
								(event claimed: TRUE)
							)
						else
							(Print 800 1)
							(event claimed: TRUE)
						)
					)
					((Said 'get,rob>')
						(cond 
							((Said '/knitting') (Print 42 49))
							((Said '/pole')
								(cond 
									(
										(and
											(== ((inventory at: iFishingPole) owner?) 204)
											(== fishermanState fisherAtHome)
										)
										(Print 42 50)
									)
									(((inventory at: iFishingPole) ownedBy: ego) (Print 42 51))
									(else (Print 42 21))
								)
							)
							((Said '/dough') (Print 42 52))
							((Said '/woman,person') (Print 42 53))
							((Said '/fisherman') (if (== fishermanState fisherAtHome) (Print 42 54)))
							((Said '/caldron') (Print 42 55))
							((Said '/can') (Print 42 56))
							((Said '/fish') (Print 42 57))
						)
					)
					((Said 'create/bed') (Print 42 58))
				)
			else
				0
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(super newRoom: newRoomNumber)
	)
)

(instance doBread of Script
	(properties)
	
	(method (init param1)
		(super init: param1)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (Timer setReal: self 5))
			(1
				(wife setLoop: 1)
				(Timer setReal: self 3)
			)
			(2
				(wife setLoop: 0)
				(= state -1)
				(self cue:)
			)
		)
	)
)

(instance givePole of Script
	(properties)
	
	(method (init param1)
		(super init: param1)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: 0)
				(HandsOff)
				(if (ego inRect: 185 115 239 124)
					(ego setMotion: MoveTo (ego x?) 125)
				)
				(wife
					view: 242
					loop: 0
					cel: 0
					setAvoider: (Avoider new:)
					setCycle: Walk
					setMotion: MoveTo 223 118 self
				)
			)
			(1
				(Face ego wife)
				(fishingPole dispose:)
				(wife
					view: 243
					setLoop: -1
					cel: 0
					setMotion: Chase ego 20 self
				)
			)
			(2
				(ego get: 17)
				(theGame changeScore: 3)
				(= gotItem 1)
				(wife
					view: 242
					loop: 0
					cel: 0
					setMotion: MoveTo 200 118 self
				)
			)
			(3
				(HandsOn)
				(wife
					view: 241
					setAvoider: 0
					loop: 0
					setCycle: Forward
					setScript: doBread
				)
			)
		)
	)
)

(instance wifeTalk of Script
	(properties)
	
	(method (init param1)
		(super init: param1)
		(client cycleSpeed: 1)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setCycle: Forward)
				((ScriptID 0 4) setReal: self 2)
			)
			(1
				(= state -1)
				(client cel: 0 setCycle: 0)
				(if (== (client loop?) 3)
					((ScriptID 0 4) setReal: self 1)
				else
					((ScriptID 0 4) setReal: self 5)
				)
			)
		)
	)
)

(instance fishermanSit of Script
	(properties)
	
	(method (init param1)
		(super init: param1)
		(client cycleSpeed: 2)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client loop: 0 cel: 0 setCycle: Forward)
				(Timer setReal: self 3)
			)
			(1
				(client loop: 1 cel: 255 setCycle: EndLoop self)
			)
			(2 (Timer setReal: self 2))
			(3
				(= state -1)
				(client setCycle: BegLoop)
				(Timer setReal: self 10)
			)
		)
	)
)

(instance wifeTacoDoco of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if talkedToFishermanWife (self cue:) (return))
				(Print 42 59)
				(= talkedToFishermanWife 1)
			)
			(1
				(if (> talkedToFishermanWife 1) (self cue:) (return))
				(Print 42 60)
				(= talkedToFishermanWife 2)
			)
			(2 (= state 1) (Print 42 61))
		)
	)
)

(instance wifeTacoDoco1 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if talkedToFishermanWife (self cue:) (return))
				(Print 42 62)
				(= talkedToFishermanWife 1)
			)
			(1 (= state 0) (Print 42 63))
		)
	)
)

(instance wifeTacoDoco2 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (< talkedToFishermanWife 0) (self cue:) (return))
				(= talkedToFishermanWife -1)
				(Print 42 64)
			)
			(1
				(= state 0)
				(Print
					(Format @str 42 65
						(if (== (wife view?) 241)
							{kneading the bread}
						else
							{knitting}
						)
					)
				)
			)
		)
	)
)

(instance fishermanTacoDoco1 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (Print 42 66))
			(1 (= state 0) (Print 42 67))
		)
	)
)

(instance fishermanTacoDoco2 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (Print 42 68))
			(1 (Print 42 69))
			(2 (= state 1) (Print 42 70))
		)
	)
)

(instance chairBase of Code
	(properties)
	
	(method (doit param1)
		(param1 brTop: (- (param1 y?) 5))
		(param1 brLeft: (- (param1 x?) 16))
		(param1 brBottom: (+ (param1 y?) 1))
		(param1 brRight: (+ (param1 x?) 5))
	)
)

(instance chairBlock of Block
	(properties)
)
