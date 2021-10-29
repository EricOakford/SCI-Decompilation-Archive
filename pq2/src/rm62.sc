;;; Sierra Script 1.0 - (do not remove this comment)
(script# 62)
(include sci.sh)
(use Main)
(use Intrface)
(use Avoider)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm62 0
)
(synonyms
	(footprint footprint)
	(diver diver cop dude diver)
	(scratch cut)
)

(local
	[str 100]
	local100
	collision
	local102
	local103
	local104
	local105
	local106
)
(procedure (localproc_025c)
	(return
		(if (ego has: 10)
			(return 1)
		else
			(Print 62 0)
			(return 0)
		)
	)
)

(procedure (localproc_027b)
	(Print &rest #at -1 22)
)

(procedure (localproc_028a)
	(Print &rest #at -1 128)
)

(instance tmpSound of Sound
	(properties)
)

(instance diver of Actor
	(properties)
)

(instance blood of View
	(properties)
)

(instance bloodView of View
	(properties)
)

(instance bains of Actor
	(properties)
)

(instance car of Actor
	(properties)
)

(instance smoke of Prop
	(properties)
)

(instance bloodBlock of Block
	(properties
		top 72
		left 70
		bottom 85
		right 95
	)
)

(instance keithAmbles of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= local103 1)
				(ego loop: 0 setMotion: 0 stopUpd:)
				(= diverState 1)
				(localproc_027b 62 1 83)
				(keith setMotion: MoveTo 150 79 self)
			)
			(1
				(localproc_028a 62 2)
				(keith setMotion: MoveTo 170 79 self)
			)
			(2
				(User canControl: 1)
				(localproc_028a 62 3)
				(= global187 1)
				(keith setMotion: MoveTo 345 79 self)
				(= gunFireState 2)
				(HandsOn)
			)
			(3 (= gunFireState 0))
		)
	)
)

(instance thereHeIs of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(diver
					view: 22
					setCycle: Walk
					setStep: 3 2
					posn: 260 100
					init:
					setMotion: MoveTo 154 100 self
				)
				(= gunFireState 3)
				(ego posn: 292 (ego y?) setMotion: MoveTo 186 100)
			)
			(1
				(tmpSound number: 12 loop: 1 play: 1)
				(if (< howFast 30)
					(diver stopUpd:)
				else
					(diver setMotion: Follow ego 600)
				)
				(localproc_028a 62 4 83)
				(localproc_028a 62 5 83)
				(= local104 1)
				(HandsOn)
			)
		)
	)
)

(instance rm62 of Room
	(properties
		picture 62
		style $0000
	)
	
	(method (init)
		(super init:)
		(self setLocales: 153 155)
		(NormalEgo)
		(HandsOn)
		(Load rsVIEW 0)
		(Load rsVIEW 20)
		(if bainsInCoveState
			(Load rsVIEW 14)
			(Load rsVIEW 76)
			(Load rsVIEW 94)
		)
		(= gunNotNeeded 0)
		(= gunFireState
			(cond 
				(bainsInCoveState 0)
				((cast contains: keith) 2)
				((cast contains: diver) 3)
				(else 1)
			)
		)
		(if (and (== gamePhase 5) (not bainsInCoveState))
			(if (== (ego view?) 17)
				(cSound number: 12 loop: 1 play:)
			else
				(cSound number: 3 loop: -1 play:)
			)
		)
		(ego
			setStep: 3 2
			posn: 300 (ego y?)
			setCycle: Walk
			illegalBits: -32768
			ignoreActors: 0
			setPri: -1
			setLoop: -1
			init:
		)
		(if (>= global111 3)
			(if removedBodyFromRiver
				((View new:)
					view: 93
					loop: 0
					cel: 0
					posn: 115 82
					init:
					addToPic:
				)
			)
			(ego observeBlocks: bloodBlock)
			(blood
				view: 93
				loop: 1
				cel: 2
				posn: 84 86
				init:
				ignoreActors:
				addToPic:
			)
			(bloodView
				view: 93
				loop: 0
				cel: 2
				posn: 0 -10
				setPri: 15
				init:
			)
			(if (and (== prevRoomNum 60) (!= diverState 13))
				(ego setMotion: MoveTo -10 (ego y?))
				(User prevDir: 7)
			)
			(if bainsInCoveState
				(bains
					view: 14
					setStep: 12 5
					posn: 94 140
					setCycle: Walk
					init:
					illegalBits: 0
					setScript: bainsScript
				)
			)
			(if (== currentCar 13)
				((= keith (Actor new:))
					view: 20
					setStep: 3 2
					posn: 330 79
					setAvoider: (Avoider new:)
					setCycle: Walk
					setMotion: 0
					init:
					stopUpd:
				)
			)
			(switch diverState
				(14
					(diver
						view: 22
						posn: 170 90
						setMotion: Follow ego 600
						init:
					)
					(if (< howFast 30) (diver setLoop: 1 stopUpd:))
					(localproc_028a 62 6 83)
				)
				(13 (self setScript: thereHeIs))
				(15
					(ego
						view: 17
						setCycle: Walk
						setStep: 3 2
						illegalBits: -32768
						ignoreActors: 0
						posn: 164 81
					)
					(diver
						view: 22
						posn: 206 84
						setMotion: Follow ego 600
						init:
					)
					(if (< howFast 30) (diver setLoop: 1 stopUpd:))
					(localproc_028a 62 5 83)
					(= local104 1)
				)
				(16
					(ego
						view: 17
						setCycle: Walk
						setStep: 3 2
						illegalBits: -32768
						ignoreActors: 0
						posn: 200 90
						setMotion: MoveTo 340 90
					)
					(localproc_028a 62 7 83)
				)
			)
		)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(local106 0)
			((< (ego x?) 4) (ego x: 4))
			((> (ego y?) 205)
				(if (Random 0 1) (Print 62 8) else (Print 62 9))
				(ego setMotion: MoveTo (ego x?) 176)
			)
			((== (ego edgeHit?) 2) (curRoom newRoom: 60))
			(
				(and
					(> (ego x?) 290)
					removedBodyFromRiver
					(!= diverState 14)
					(cast contains: diver)
				)
				(= diverState 14)
				(localproc_027b 62 10)
			)
			(
			(and local105 (not (ego inRect: 58 70 135 100))) (= local105 0) (bloodView posn: 0 -10 forceUpd:))
		)
	)
	
	(method (handleEvent event &tmp temp0)
		(if (event claimed?) (return))
		(switch (event type?)
			(evKEYBOARD
				(= temp0 (event message?))
				(if
					(and
						(== (ego view?) 17)
						(or
							(== temp0 KEY_F6)
							(== temp0 KEY_F8)
							(== temp0 KEY_F10))
					)
					(localproc_027b 62 11)
					(event claimed: 1)
				else
					(event claimed: 0)
				)
			)
			(evSAID
				(if (Said 'call/coroner')
					(cond 
						(removedBodyFromRiver (Print 62 12))
						(
						(and (cast contains: keith) (keith onControl:)) (Print 62 13))
						(else (Print 62 14))
					)
				)
				(if (>= global111 3)
					(cond 
						((Said 'check,look/air,gauge,equipment')
							(if (!= (ego view?) 17)
								(Print 62 15)
							else
								(Print 62 16)
							)
						)
						((Said 'affirmative')
							(if local104
								(= local104 0)
								(cond 
									((== diverState 14) (Print 62 17))
									(removedBodyFromRiver (Print 62 18))
								)
							else
								(event claimed: 0)
							)
						)
						((Said 'n')
							(if local104
								(= local104 0)
								(cond 
									((== diverState 14) (Print 62 19))
									(removedBodyFromRiver (Print 62 20))
								)
							else
								(event claimed: 0)
							)
						)
						(
							(or
								(Said 'dust,powder[/can]')
								(Said '(apply,use)<dust,powder[/can]')
								(Said 'get,remove,hoist/fingerprint,print[<finger]')
							)
							(if (localproc_025c)
								(if (ego inRect: 248 97 293 121)
									(global122 startUpd: setPri: 0)
									(localproc_028a 62 21 83)
									(global122 setPri: 15 stopUpd:)
									(global120 startUpd: setPri: 0)
									(localproc_028a 62 22 83)
									(global120 setPri: 14 stopUpd:)
								else
									(localproc_027b 62 23)
								)
							)
						)
						((or (Said 'use/camera') (Said 'get/painting'))
							(if (localproc_025c)
								(global124 startUpd: setPri: 0)
								(SolvePuzzle 1 72)
								(localproc_027b 62 24 83)
								(global124 setPri: 14 stopUpd:)
							)
						)
						((ego inRect: 58 70 135 100)
							(cond 
								((Said 'check,feel/sign,breathing') (localproc_028a 62 25))
								(
									(or
										(Said 'get,remove,pick/blood')
										(Said 'get,remove,pick/sample<blood')
										(Said 'get,pick/sample/blood')
										(Said 'use/dropper,vial')
										(Said 'deposit/blood/vial')
									)
									(if (localproc_025c)
										(if (Btst 144)
											(localproc_028a 62 26)
										else
											(global119 startUpd: setPri: 0)
											(global118 startUpd: setPri: 0)
											(SolvePuzzle 2)
											(localproc_028a 62 27 83)
											(ego get: 28)
											(Bset 144)
											(global119 setPri: 13 stopUpd:)
											(Print 62 28 #draw)
											(if (and (== currentCar 13) (== diverState 0))
												(rm62 setScript: keithAmbles)
											)
										)
									)
								)
								(
									(or
										(Said 'make,use/plaster,cast')
										(Said
											'get,cast/(print<feet),footprint,print,(sample<(print<feet),footprint)'
										)
									)
									(if (localproc_025c)
										(if (ego has: 17)
											(localproc_028a 62 29)
										else
											(gDView startUpd: setPri: 0)
											(localproc_028a 62 30 83)
											(localproc_028a 62 31)
											(ego get: 17)
											(SolvePuzzle 2)
											(gDView setPri: 13 stopUpd:)
											(localproc_028a 62 32 83)
											(if (and (== currentCar 13) (== diverState 0))
												(rm62 setScript: keithAmbles)
											)
										)
									)
								)
								(
									(or
										(Said 'use,get,remove/baggie')
										(Said 'get,get/hair,dirt,rock')
									)
									(if (localproc_025c) (localproc_027b 62 33))
								)
								(removedBodyFromRiver
									(cond 
										((Said 'throw[<back]/body') (localproc_028a 62 34))
										((Said 'frisk,look/body,luis,body') (localproc_028a 62 35))
										((Said 'look,view/injury,hole,head') (localproc_028a 62 36))
										((Said 'look,view/scratch,neck') (localproc_028a 62 37))
										(
											(Said
												'frisk,look/panties,cloth,arm,leg,feet,hand,toe,finger'
											)
											(localproc_028a 62 38)
										)
										((Said '/clock') (localproc_028a 62 39))
									)
								)
								(
									(and
										(not removedBodyFromRiver)
										(Said 'look,look,frisk,get,view/body,injury,scratch,neck')
									)
									(Print 62 40)
								)
							)
						)
						(
							(and
								removedBodyFromRiver
								(Said
									'look,look,frisk,view/body,body,luis,injury,scratch,neck'
								)
							)
							(NotClose)
						)
						(
							(and
								(not removedBodyFromRiver)
								(Said 'look,view,frisk/body,body,luis,injury,neck')
							)
							(Print 62 40)
						)
					)
				)
				(cond 
					((Said 'dive')
						(if (!= (ego view?) 17)
							(Print 62 41)
						else
							(Print 62 16)
						)
					)
					((Said 'get,remove/garbage')
						(if (ego inRect: 248 97 293 121)
							(localproc_028a 62 42)
						else
							(localproc_027b 62 43)
						)
					)
					(
					(Said 'frisk,move,dig,(look<(through,below))/can,garbage')
						(if (ego inRect: 248 97 293 121)
							(if
								(and
									(== ((inventory at: 26) owner?) 62)
									(>= global111 3)
								)
								(localproc_028a 62 44)
								(= local102 1)
							else
								(localproc_028a 62 45)
							)
						else
							(localproc_027b 62 43)
						)
					)
					((Said 'move,get,hoist/can[<garbage]')
						(if (ego inRect: 248 97 293 121)
							(localproc_028a 62 46)
						else
							(localproc_027b 62 43)
						)
					)
					((Said 'smell')
						(if (ego inRect: 248 97 293 121)
							(localproc_028a 62 47)
						else
							(localproc_027b 62 48)
						)
					)
					((Said 'get/cloth')
						(cond 
							((not (InRoom 26)) (Print 62 49))
							((ego inRect: 248 97 293 121)
								(if (and local102 (InRoom 26))
									(SolvePuzzle 1)
									(localproc_028a 62 50)
									(localproc_028a 62 51)
									(ego get: 26)
								else
									(localproc_027b 62 52)
								)
							)
							(else (localproc_027b 62 43))
						)
					)
					((Said 'get/clue')
						(if (and local103 (ego inRect: 58 70 135 100))
							(localproc_028a 62 53)
						)
					)
					(
					(and (Said 'chat,ask/diver') (cast contains: diver))
						(if (< (ego distanceTo: diver) 60)
							(localproc_028a 62 54)
						else
							(localproc_027b 62 55)
						)
					)
					((Said 'look/diver')
						(if (cast contains: diver)
							(localproc_027b 62 56)
						else
							(localproc_027b 62 57)
						)
					)
					((Said 'look,read/name,label')
						(if (ego has: 26)
							(SolvePuzzle 2 73)
							(localproc_028a 62 58)
						else
							(DontHave)
						)
					)
					((Said 'get,make/sample,cast') (Print 62 59))
					((Said 'look>')
						(cond 
							((Said '/briefcase') (event claimed: 0))
							(
								(or
									(Said '/(print<feet)')
									(Said '/mark,dirt,footprint,clue,blood')
									(Said '<down')
								)
								(if
								(and (ego inRect: 58 70 135 100) (>= global111 3))
									(bloodView posn: 39 107 forceUpd:)
									(= local105 1)
									(SolvePuzzle 1 71)
									(localproc_028a 62 60 83)
									(localproc_028a 62 61)
									(if (and (== currentCar 13) (== diverState 0))
										(rm62 setScript: keithAmbles)
									)
								else
									(localproc_027b 62 62)
								)
							)
							((Said '/auto')
								(if (and (cast contains: car) (not local100))
									(Print 62 63)
								else
									(Print 62 64)
								)
							)
							((Said '/plate')
								(if (and (cast contains: car) (not local100))
									(Print 62 65)
								else
									(Print 62 66)
								)
							)
							((or (Said '/garbage') (Said '/can[<garbage]'))
								(if (ego inRect: 248 97 293 121)
									(localproc_028a 62 67)
									(localproc_028a 62 68)
								else
									(localproc_027b 62 43)
								)
							)
							((Said '/sample[<blood]')
								(if (Btst 144)
									((inventory at: 28) showSelf:)
								else
									(DontHave)
								)
							)
							((Said '/tree<behind') (localproc_027b 62 69))
							((Said '/bush<below') (localproc_027b 62 70))
							((Said '/tree') (localproc_027b 62 71))
							((Said '[<at,around][/!*,cove,area]') (localproc_027b 62 72))
						)
					)
				)
			)
		)
	)
)

(instance bainsScript of Script
	(properties)
	
	(method (init)
		(super init:)
		(if bainsInCoveState
			(car
				view: 94
				setStep: 16 4
				setLoop: 1
				setCel: 0
				posn: 3 201
				init:
				illegalBits: 0
				ignoreActors:
				stopUpd:
			)
			(smoke
				view: 94
				loop: 2
				cel: 0
				init:
				ignoreActors:
				stopUpd:
			)
		)
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(< 17 (- (ego x?) (car x?)))
				(< (- (ego x?) (car x?)) 65)
				(> 14 (- (car y?) (ego y?)))
				(< (self state?) 5)
			)
			(self changeState: 5)
		)
		(if (!= (self state?) 4)
			(smoke posn: (- (car x?) 48) (+ (car y?) 6))
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(bains setMotion: MoveTo -26 140 self)
			)
			(1
				(bains stopUpd:)
				(= seconds 3)
			)
			(2
				(tmpSound number: 39 loop: 1 play:)
				(= bainsInCoveState 0)
				(car
					setMotion:
						MoveTo
						(ego x?)
						(if (> (ego y?) 132) (+ (ego y?) 10) else 141)
						self
				)
				(smoke setCycle: Forward)
			)
			(3
				(car setMotion: MoveTo 430 (car y?) self)
			)
			(4
				(car stopUpd:)
				(smoke stopUpd:)
				(= local100 1)
			)
			(5
				(car setMotion: MoveTo 430 (+ (ego y?) 28))
				(if (== currentCar 13)
					(keith setMotion: Follow ego 50 startUpd:)
				)
				((= collision (Actor new:))
					view: 76
					posn: (ego x?) (ego y?)
					loop: 5
					cel: 0
					setMotion: 0
					cycleSpeed: 2
					init:
					setCycle: EndLoop self
				)
				(ego dispose:)
				(= local106 1)
			)
			(6
				(collision
					setLoop: 6
					cel: 0
					setMotion: MoveTo (+ (collision x?) 20) (collision y?)
					setCycle: EndLoop self
				)
			)
			(7
				(collision setLoop: 7 setCel: 0 stopUpd: addToPic:)
				(= seconds 2)
				(User canInput: 0)
			)
			(8
				(if (== currentCar 13)
					(keith loop: 1)
					(localproc_027b 62 73 83)
				)
				(= seconds 2)
			)
			(9
				(Print 62 74 #mode 1)
				(if (== currentCar 13)
					(EgoDead
						{Don't worry, though; Keith will explain in his report how he heroically 'watched your back'._}
					)
				else
					(EgoDead
						{Too bad, soooo sad. There were no witnesses to your untimely demise. But don't worry. Just restore and try again._}
					)
				)
			)
		)
	)
)
