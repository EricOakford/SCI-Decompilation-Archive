;;; Sierra Script 1.0 - (do not remove this comment)
(script# 15)
(include game.sh)
(use Main)
(use Intrface)
(use Reverse)
(use Extra)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm015 0
)

(local
	local0
)
(instance rm015 of Room
	(properties
		style HSHUTTER
	)
	
	(method (init &tmp [temp0 50])
		(if ((inventory at: 3) ownedBy: 15)
			(self picture: 15)
		else
			(self picture: 157)
		)
		(self setLocales: JUNKBAY)
		(HandsOff)
		(Load VIEW 19)
		(Load VIEW 29)
		(Load VIEW 32)
		(Load VIEW 6)
		(Load VIEW 33)
		(Load VIEW 34)
		(Load VIEW 35)
		(if
		(and (not (InRoom iLadder 8)) (not (ego has: iLadder)))
			(Load VIEW 15)
		else
			(Load VIEW 288)
		)
		(if (!= prevRoomNum 3) (Load SOUND 56))
		(if
		(and climbedOutOfReactorRoom (== prevRoomNum 3))
			(Load SOUND 11)
		)
		(Load SOUND 12)
		(= global132 0)
		(super init:)
		(self setScript: rmScript)
	)
	
	(method (doit)
		(if (InRoom iReactor)
			(if
				(and
					(== (ego view?) 0)
					(or
						(== (ego onControl: 0) 4)
						(== (ego onControl: 0) 5)
					)
				)
				(ego view: 32)
			)
			(if
			(and (== (ego view?) 32) (== (ego onControl: 0) 1))
				(ego view: 0)
			)
		)
		(if
			(and
				(InRoom iLadder 8)
				(not isHandsOff)
				(ego inRect: 165 150 175 186)
			)
			(HandsOff)
			(self setScript: mugScript)
		)
		(super doit:)
	)
	
	(method (handleEvent event)
		(if
			(or
				(!= (event type?) saidEvent)
				programControl
				(event claimed?)
			)
			(return)
		)
		(cond 
			((Said 'look>')
				(cond 
					((Said '[<around,at,in][/area,!*]') (if (InRoom iReactor) (Print 15 0) else (Print 15 1)))
					((Said '/lamp') (if (InRoom iReactor) (Print 15 2) else (Print 15 3)))
					((Said '/cable') (Print 15 4))
					((and local0 (Said '/hook')) (Print 15 5))
					((and local0 (Said '/mice,animal,animal')) (Print 15 6))
					((or (Said '/hal<cable') (Said '/cable<hal'))
						(if (ego inRect: 0 125 38 155)
							(if (InRoom iReactor) (Print 15 7) else (Print 15 8))
						else
							(Print 15 9)
						)
					)
					((Said '/cavity')
						(cond 
							((ego inRect: 0 125 38 155)
								(Print
									(Format @invStr 15 10
										(cond 
											(
												(and
													(InRoom iReactor)
													(InRoom iGlowingGem)
													(InRoom iWire)
												)
												{a tiny reactor which seems to be providing power for the lights, a formerly lustrous gem, and an unconnected wire}
											)
											((and (InRoom iReactor) (InRoom iGlowingGem))
												{a tiny reactor which seems to be providing power for the lights and a formerly lustrous gem}
											)
											((and (InRoom iReactor) (InRoom iWire))
												{a tiny reactor which seems to be providing power for the lights and an unconnected wire}
											)
											((and (InRoom iGlowingGem) (InRoom iWire)) {a formerly lustrous gem, and an unconnected wire})
											((InRoom iReactor)
												{a tiny reactor which seems to be providing power for the lights}
											)
											((InRoom iWire) {an unconnected wire})
											((InRoom iGlowingGem) {a formerly lustrous gem})
											(else {nothing but crusty wire ends})
										)
									)
								)
							)
							((ego inRect: 248 69 265 77) (Print 15 11))
							(else (Print 15 12))
						)
					)
					(
					(or (Said '<up[/ceiling,!*]') (Said '[<up]/ceiling')) (Print 15 13))
					((Said '/left,partition<w') (Print 15 14))
					((Said 'climb/support,cover') (Print 15 15))
					((Said '/system,door[<garbage]') (Print 15 16))
					((Said 'look/ladder')
						(if (InRoom iLadder)
							(Print 15 17)
						else
							(event claimed: 0)
						)
					)
					(
					(or (Said '<down[/deck,!*]') (Said '[<down]/deck')) (Print 15 18))
					((Said '/partition[<leech,north,e]') (Print 15 19))
					((Said '/heap,artifact,chunk') (Print 15 20))
					((Said '/support') (Print 15 21))
					((Said '/cover') (Print 15 22))
				)
			)
			(
			(or (Said 'climb[/ladder]') (Said 'go<up/ladder'))
				(if
				(and (ego inRect: 248 69 265 77) (InRoom iLadder))
					(ego setScript: ladderScript)
				else
					(Print 15 23)
				)
			)
			((Said 'get>')
				(cond 
					((Said '/all')
						(if (ego inRect: 0 125 38 155)
							(if (InRoom iGlowingGem)
								(ego get: iGlowingGem)
								(theGame changeScore: 5)
							)
							(if (InRoom iWire)
								(ego get: iWire)
								(theGame changeScore: 5)
							)
							(if (InRoom iReactor)
								(ego get: iReactor)
								(Print 15 24)
								(reactorScript init:)
								(theGame changeScore: 15)
							else
								(Print 15 24)
							)
						else
							(NotClose)
						)
					)
					((Said '/ladder')
						(if
							(and
								(ego inRect: 248 69 265 77)
								((inventory at: iLadder) ownedBy: 15)
							)
							(ladder dispose:)
							(ego get: iLadder)
							(theGame changeScore: 10)
							(Print 15 25)
							(Print 15 26)
						else
							(Print 15 27)
						)
					)
					((or (Said '/hal<cable') (Said '/cable<hal'))
						(if (ego inRect: 0 125 38 155)
							(if (InRoom iWire) (Print 15 7) else (Print 15 28))
						else
							(Print 15 28)
						)
					)
					((Said '/artifact,support,cover') (Print 15 29))
					((Said '/generator')
						(if (InRoom iReactor)
							(if (ego inRect: 0 125 38 155)
								(theGame changeScore: 15)
								(Print 15 30)
								(reactorScript init:)
							else
								(Print 15 31)
							)
						else
							(Print 15 32)
						)
					)
					((Said '/cable')
						(if (InRoom iWire)
							(if (ego inRect: 0 125 38 155)
								(Print 15 24)
								(ego get: iWire)
								(theGame changeScore: 5)
							else
								(NotClose)
							)
						else
							(event claimed: FALSE)
						)
					)
					((Said '/crystal')
						(if (InRoom iGlowingGem)
							(if (ego inRect: 0 125 38 155)
								(Print 15 24)
								(ego get: iGlowingGem)
								(theGame changeScore: 5)
							else
								(NotClose)
							)
						else
							(event claimed: FALSE)
						)
					)
				)
			)
			(
				(Said
					'climb,crawl[<through,up,on]/partition,cavity,system'
				)
				(Print 15 33)
			)
			((Said 'use,afix,(jar<up)/generator')
				(if (or (ego has: iReactor) (InRoom iReactor))
					(Print 15 34)
				else
					(event claimed: 0)
				)
			)
			(
				(Said
					'erect,stand,drop,place,use/ladder[/cavity[<by,in]]'
				)
				(if (ego inRect: 170 66 292 97)
					(if (ego has: iLadder)
						(ego setScript: putLadderScript)
					else
						(Print 15 35)
					)
				else
					(Print 15 36)
				)
			)
			((Said 'open/system,door') (Print 15 37))
		)
	)
	
	(method (newRoom newRoomNumber)
		(DisposeScript EXTRA)
		(super newRoom: newRoomNumber)
	)
)

(instance rmScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(doors init:)
				(if ((inventory at: iLadder) ownedBy: 15)
					(ladder init: stopUpd:)
				)
				(if (== prevRoomNum 3)
					(ego setScript: ladderScript)
					(doors stopUpd:)
				else
					(= cycles 3)
				)
			)
			(1 (doors setCycle: EndLoop self))
			(2
				(ego
					view: 35
					posn: 70 120
					setPri: 8
					setLoop: 0
					cel: 0
					cycleSpeed: 1
					init:
					setMotion: 0
					setCycle: EndLoop self
				)
			)
			(3
				(doors stopUpd:)
				(ego setLoop: 1 cel: 0 setCycle: EndLoop self)
				(thump play:)
			)
			(4
				(ego hide:)
				(doors setCycle: BegLoop self)
			)
			(5
				(doors addToPic:)
				(Print 15 38)
				(= seconds 2)
			)
			(6
				(HandsOn)
				(ego
					view: (if (InRoom iReactor) 32 else 6)
					illegalBits: cWHITE
					posn: 80 132
					setLoop: -1
					setPri: -1
					loop: 2
					setStep: 3 2
					show:
					cycleSpeed: 0
					setCycle: Walk
				)
				(if
				(and (InRoom iReactor) (not (InRoom iLadder 8)))
					(rat1 setScript: ratsScript)
				)
				(if
				(and climbedOutOfReactorRoom (== prevRoomNum 3))
					(theMusic number: 11 loop: -1 play:)
				)
				(= programControl FALSE)
			)
		)
	)
)

(instance reactorScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ladder setLoop: 1 forceUpd:)
				(ego get: iReactor view: 6)
				(curRoom drawPic: 157)
				(doors setLoop: 1 init: addToPic:)
				(rat1 setLoop: 4 init: addToPic:)
				(rat2 setLoop: 4 init: addToPic:)
				(rat3 setLoop: 4 init: addToPic:)
				(eye1 setLoop: 5)
				(eye2 setLoop: 6)
				(eye3 setLoop: 7)
			)
		)
	)
)

(instance mugScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Print 15 39)
				(Print 15 40)
				(ego view: 288 setLoop: 0 setMotion: MoveTo 170 145 self)
			)
			(1
				(ego setLoop: 1 setCycle: Forward)
				(mugRat1
					init:
					setCycle: Walk
					setMotion: MoveTo 165 145 self
				)
				(mugRat2 init: setCycle: Walk setMotion: MoveTo 175 145)
			)
			(2
				(cloud init:)
				(rumble play:)
				(ego hide:)
				(mugRat1 hide:)
				(mugRat2 hide:)
				(= seconds 6)
			)
			(3
				(ego
					setLoop: 2
					posn: (- (ego x?) 20) (- (ego y?) 7)
					show:
					stopUpd:
				)
				(mugRat1
					setLoop: 1
					x: (- (mugRat1 x?) 50)
					show:
					setMotion: MoveTo 20 155 self
				)
				(mugRat2
					setLoop: 0
					x: (+ (mugRat2 x?) 50)
					show:
					setMotion: MoveTo 320 155
				)
				(cloud dispose:)
				(rumble stop:)
			)
			(4
				(Print 15 41)
				(EgoDead 901 0 2 3)
			)
		)
	)
)

(instance ladderScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 19
					setLoop: (if (InRoom iReactor) 0 else 1)
					setPri: 4
					illegalBits: 0
					ignoreActors: 1
				)
				(if (ego inRect: 248 69 265 77)
					(ego posn: 258 73 setMotion: MoveTo 258 23 self)
				else
					(ego
						posn: 258 23
						init:
						setMotion: MoveTo 258 73 self
						setCycle: Reverse
					)
				)
			)
			(1
				(if (ego inRect: 248 69 265 77)
					(ego
						setPri: -1
						setLoop: -1
						view: (if (not (InRoom iReactor)) 6 else 0)
						setCycle: Walk
						illegalBits: cWHITE
						ignoreActors: 0
					)
					(User canControl: TRUE canInput: TRUE)
					(= programControl FALSE)
				else
					(curRoom newRoom: 3)
				)
			)
		)
	)
)

(instance putLadderScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(User canControl: FALSE)
				(= programControl TRUE)
				(ego setMotion: MoveTo 258 76 self)
			)
			(1
				(ego loop: 3)
				(ladder
					setLoop: (if (InRoom iReactor) 0 else 1)
					init:
					stopUpd:
				)
				(PutInRoom iLadder)
				(theGame changeScore: -10)
				(= programControl FALSE)
				(User canControl: TRUE)
			)
		)
	)
)

(instance ratsScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ratTune play:)
				(= local0 1)
				(rat1 posn: -24 242 init: setMotion: MoveTo 24 194 self)
			)
			(1
				(eye1 init: startExtra:)
				(rat2 posn: 348 227 init: setMotion: MoveTo 297 176 self)
			)
			(2
				(rat2 addToPic:)
				(eye2 init: startExtra:)
				(rat3 init: posn: 303 2 setMotion: MoveTo 261 44 self)
			)
			(3
				(rat3 addToPic:)
				(eye3 init: startExtra:)
			)
		)
	)
)

(instance doors of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 33
			loop: (if (InRoom iReactor) 0 else 1)
			cel: 0
			posn: 64 110
			setPri: 8
			cycleSpeed: 2
		)
	)
)

(instance ladder of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 29
			setLoop: (if (InRoom iReactor) 0 else 1)
			setCel: 0
			posn: 256 70
		)
	)
)

(instance rat1 of Actor
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 34
			setLoop: (if (InRoom iReactor) 0 else 4)
			setCel: 0
			setStep: 3 3
			illegalBits: 0
			setPri: 15
			setCycle: 0
		)
	)
)

(instance rat2 of Actor
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 34
			setLoop: (if (InRoom iReactor) 0 else 4)
			setCel: 1
			setStep: 3 3
			illegalBits: 0
			setPri: 15
			setCycle: 0
		)
	)
)

(instance rat3 of Actor
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 34
			setLoop: (if (InRoom iReactor) 0 else 4)
			setCel: 2
			setStep: 3 3
			illegalBits: 0
			ignoreHorizon:
			setPri: 15
			setCycle: 0
		)
	)
)

(instance eye1 of Extra
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 34
			loop: (if (InRoom iReactor) 1 else 5)
			cel: 0
			posn: 18 171
			setPri: 15
			cycleSpeed: 2
			pauseCel: 0
			minPause: 20
			maxPause: 50
			minCycles: 2
			maxCycles: 4
			isExtra: TRUE
		)
	)
)

(instance eye2 of Extra
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 34
			loop: (if (InRoom iReactor) 2 else 6)
			cel: 0
			posn: 285 153
			setPri: 15
			cycleSpeed: 2
			pauseCel: 0
			minPause: 20
			maxPause: 50
			minCycles: 2
			maxCycles: 4
			isExtra: TRUE
		)
	)
)

(instance eye3 of Extra
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 34
			loop: (if (InRoom iReactor) 3 else 7)
			cel: 0
			posn: 249 30
			setPri: 15
			cycleSpeed: 2
			pauseCel: 0
			minPause: 50
			maxPause: 100
			minCycles: 2
			maxCycles: 4
			isExtra: TRUE
		)
	)
)

(instance mugRat1 of Actor
	(properties
		view 15
	)
	
	(method (init)
		(super init:)
		(self
			setLoop: 0
			ignoreActors: TRUE
			illegalBits: 0
			posn: 20 155
			setStep: 12 2
		)
	)
)

(instance mugRat2 of Actor
	(properties
		view 15
	)
	
	(method (init)
		(super init:)
		(self
			setLoop: 1
			ignoreActors: TRUE
			illegalBits: 0
			posn: 320 155
			setStep: 12 2
		)
	)
)

(instance cloud of Prop
	(properties
		view 15
	)
	
	(method (init)
		(super init:)
		(self
			setLoop: 3
			ignoreActors: 1
			posn: (- (ego x?) 1) (- (ego y?) 1)
			setPri: (+ (ego priority?) 1)
			setCycle: Forward
		)
	)
)

(instance ratTune of Sound
	(properties
		number 12
	)
)

(instance thump of Sound
	(properties
		number 56
		priority 2
	)
)

(instance rumble of Sound
	(properties
		number 60
		priority 2
		loop -1
	)
)
