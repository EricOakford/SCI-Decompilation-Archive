;;; Sierra Script 1.0 - (do not remove this comment)
(script# 70)
(include game.sh)
(use Main)
(use ThrowFlameDart)
(use ThrowDagger1)
(use CastCalm)
(use CastDazz)
(use Intrface)
(use LoadMany)
(use Sound)
(use Save)
(use Motion)
(use Game)
(use Invent)
(use User)
(use Actor)
(use System)

(public
	rm70 0
)

(local
	eatingShrooms
	faeriesOnScreen
	theNewAFaeryWindow
	local3
	local4
	[mush 8]
	[mushX 8] = [62 119 91 145 134 104 75 40]
	[mushY 8] = [109 108 108 107 101 98 97 101]
	[faery 5]
	[faeryX 5] = [74 143 52 90 110]
	[faeryY 5] = [109 120 122 99 108]
	[faeryTextColor 5] = [1 11 6 5 8]
	[faeryBackColor 5] = [14 2 10 15 12]
	[faeryWindow 5]
	[faeryScript 5]
	[chaseScript 5]
	local69
	[local70 5] = [35 250 265 140 225]
	[local75 5] = [30 35 125 50 45]
	local80
	local81
	local82
	local83
	local84
	local85 =  137
	local86 =  137
	local87
	askedForDust
	local89 =  -1
	local90
	local91 =  13
	local92 =  22
	local93 =  32
	local94 =  38
	local95 =  47
	local96 =  59
	local97 =  69
	local98 =  78
	local99 =  100
	local100 =  104
	local101 =  113
	local102 =  121
	local103 =  128
	local104 =  135
	local105 =  141
	local106 =  151
	local107
	local108
	local109
	mushroomPalette
	local111
)
(procedure (AddFaeryWindow &tmp i)
	(= i 0)
	(while (< i 5)
		(= [faeryWindow i] (aFaeryWindow new:))
		(if (< numColors 8)
			([faeryWindow i] color: vBLACK back: vWHITE)
		else
			([faeryWindow i]
				color: [faeryTextColor i]
				back: [faeryBackColor i]
			)
		)
		(++ i)
	)
)

(procedure (FairySays seconds &tmp theX theY)
	(= theNewAFaeryWindow [faeryWindow (Random 0 4)])
	(= theX (Random 5 210))
	(= theY
		(if (< (ego y?) 140)
			(Random (ego y?) 140)
		else
			(Random 5 (- (ego y?) 80))
		)
	)
	(cls)
	(Print
		&rest
		#at theX theY
		#width 100
		#mode teJustCenter
		#dispose
		#time seconds
		#window theNewAFaeryWindow
	)
)

(procedure (AddChaseScript &tmp i)
	(Bset GOT_FAIRIES_ATTENTION)
	(= i 0)
	(while (< i 5)
		(= [chaseScript i] (Clone aChaseScript))
		([faery i]
			setStep: 6 4
			setScript: [chaseScript i] 0 ;(= [chaseScript i] (Clone aChaseScript))
		)
		(++ i)
	)
)

(procedure (AddFaeryDanceScript &tmp i)
	(Bclr GOT_FAIRIES_ATTENTION)
	(= i 0)
	(while (< i 5)
		(= [faeryScript i] (aFaeryScript new:))
		([faery i]
			setStep: 3 2
			setScript: [faeryScript i] 0
				;(= [faeryScript i] (aFaeryScript new:))
		)
		(++ i)
	)
)

(procedure (FaeriesHostile &tmp i temp1)
	(if faeriesOnScreen
		(Bclr GOT_FAIRIES_ATTENTION)
		(= local69 80)
		(= i 0)
		(while (< i 5)
			(= [faeryScript i] (aFaeryScript new:))
			(= temp1 (Random 0 4))
			([faery temp1]
				posn: [local70 temp1] [local75 temp1]
				setScript: [faeryScript i] 0 temp1
			)
			(++ i)
		)
	)
)

(procedure (AddFaeries &tmp i)
	;EO: These procedures did not decompile correctly. This has been fixed.
	(= i 0)
	(while (< i 5)
		(= [faeryScript i] (aFaeryScript new:))
		(= [faery i] (aFaery new:))
		([faery i]
			setLoop: i
			cel: 0
			ignoreActors:
			ignoreHorizon:
			posn: [faeryX i] [faeryY i]
			init:
			setCycle: Forward
			setScript: [faeryScript i] 0
		)
		(++ i)
	)
)

(procedure (AddShrooms &tmp i)
	(= i 0)
	(while (< i 8)
		(= [mush i] (Clone aMush))
		([mush i]
			setLoop: 5
			setCel: i
			posn: [mushX i] [mushY i]
			init:
			stopUpd:
		)
		(++ i)
	)
)

(instance aFaery of Actor
	(properties
		z 25
		view vFaery
		illegalBits $0000
	)
)

(instance aMush of View
	(properties
		view vFaery
	)
)

(instance aFaeryWindow of SysWindow)

(instance faeryMusic of Sound
	(properties
		number 40
		priority 1
		loop -1
	)
)

(instance egoBoogie of Sound
	(properties
		number 49
		priority 2
		loop -1
	)
)

(instance bopTilYouDrop of Sound
	(properties
		number 64
		priority 2
		loop -1
	)
)

(instance rm70 of Room
	(properties
		picture 70
		style HSHUTTER
		horizon 74
		north 62
		east 71
		south 77
		west 69
	)
	
	(method (init)
		(if (= faeriesOnScreen Night)
			(LoadMany VIEW vFaery vEgoDance)
			(Load TEXT 296)
			(LoadMany SOUND 40 64)
		)
		(LoadMany VIEW vEgoThrowing vEgoGetFaeryDust)
		(if faeriesOnScreen
			(AddFaeries)
			(AddFaeryWindow)
			(keyDownHandler add: self)
			(mouseDownHandler addToFront: self)
			(directionHandler add: self)
			(cSound stop:)
			(faeryMusic init: play:)
			(egoBoogie init:)
			(bopTilYouDrop init:)
		else
			(= local3 1)
			(= currentPalette 0)
		)
		(AddShrooms)
		(super init:)
		(StatusLine enable:)
		(= local80 -1)
		(NormalEgo)
		(switch prevRoomNum
			(62
				(ego posn: 130 75 init: setMotion: MoveTo 130 85)
			)
			(71
				(ego posn: 318 140 init: setMotion: MoveTo 240 140)
			)
			(69
				(ego posn: 1 140 init: setMotion: MoveTo 35 140)
			)
			(else 
				(ego posn: 120 188 init: setMotion: MoveTo 120 170)
			)
		)
		(self setLocales: FOREST)
	)
	
	(method (doit)
		(cond 
			((== local69 70)
				(-- local69)
				(= local107 1)
				(= local82 (+ (= local80 local97) 8))
				(= local81 8)
				(rm70 setScript: faeryTalk)
			)
			((> local69 1) (-- local69))
			((== local69 1) (= local69 0))
		)
		(cond 
			((> local4 1) (-- local4))
			((== local4 1)
				(= local4 0)
				(= local107 1)
				(= local82 (+ (= local80 local91) 8))
				(= local81 2)
				(self setScript: faeryTalk)
			)
		)
		(if
			(and
				(== (ego onControl: origin) cYELLOW)
				(== (User canControl:) TRUE)
				(not local87)
				faeriesOnScreen
			)
			(= local87 1)
			(AddChaseScript)
			(= local4 0)
			(= local107 1)
			(= local82 (+ (= local80 local96) 9))
			(= local81 7)
			(self setScript: faeryTalk)
		)
		(cond 
			((and (> local80 local89) (not local3)) (= local3 1))
			((and (== local81 8) (== (rm70 script?) 0))
				(= local107 1)
				(= local82 (+ (= local80 local92) 9))
				(= local81 3)
				(self setScript: faeryTalk)
			)
		)
		(super doit:)
	)
	
	(method (dispose &tmp temp0)
		(if faeriesOnScreen
			(cls)
			(= temp0 0)
			(while (< temp0 5)
				([faeryWindow temp0] dispose:)
				(++ temp0)
			)
		)
		(if local111 (HighPrint 70 0))
		(super dispose:)
	)
	
	(method (handleEvent event &tmp spell)
		(if
		(and (== (event type?) direction) (not local3))
			(event claimed: TRUE)
			(= local3 1)
			(if (Btst DANCED_FOR_FAIRIES)
				(= local82 (+ (= local80 local106) 9))
				(= local81 17)
			else
				(= local82 (+ (= local80 local90) 12))
				(= local81 1)
			)
			(AddChaseScript)
			(self setScript: faeryTalk)
		)
		(if
		(and (== (event type?) mouseDown) (not local3))
			(event claimed: TRUE)
			(= local3 1)
			(if (Btst DANCED_FOR_FAIRIES)
				(= local82 (+ (= local80 local106) 9))
				(= local81 17)
			else
				(= local82 (+ (= local80 local90) 12))
				(= local81 1)
			)
			(AddChaseScript)
			(self setScript: faeryTalk)
		)
		(if
			(and
				(== (event type?) keyDown)
				(== (event message?) ENTER)
				script
			)
			(script cue:)
			(event claimed: TRUE)
		)
		(if (== (event type?) saidEvent)
			(if (> local4 1)
				(cond 
					((Said 'affirmative,please,dance')
						(= local4 0)
						(= local107 1)
						(= local82 (+ (= local80 local91) 8))
						(= local81 2)
						(self setScript: faeryTalk)
					)
					((Said 'n')
						(= local4 0)
						(= local107 1)
						(= local82 (+ (= local80 local92) 9))
						(= local81 3)
						(self setScript: faeryTalk)
					)
					(else
						(HighPrint 70 1)
						;Don't you want to dance?
						(event claimed: TRUE))
				)
			else
				(if (not local3)
					(event claimed: TRUE)
					(= local3 1)
					(if (Btst DANCED_FOR_FAIRIES)
						(= local82 (+ (= local80 local106) 9))
						(= local81 17)
					else
						(= local82 (+ (= local80 local90) 12))
						(= local81 1)
					)
					(AddChaseScript)
					(self setScript: faeryTalk)
				)
				(cond 
					((super handleEvent: event))
					((Said 'dance')
						(if faeriesOnScreen
							(if (and (not script) (== (ego script?) 0))
								(ego setScript: cuteDance)
							)
						else
							(HighPrint 70 2)
							;What for?  There's no one to dance with.
						)
					)
					((or (Said 'fight,kill,beat') (Said 'use/blade,dagger'))
						(if faeriesOnScreen
							(AddChaseScript)
							(= local107 1)
							(= local82 (+ (= local80 local105) 9))
							(= local81 16)
							(self setScript: faeryTalk)
						else
							(HighPrint 70 3)
							;What for?
						)
					)
					((Said 'chat')
						(if (and faeriesOnScreen (== local4 0))
							(if (Btst GOT_FAIRIES_ATTENTION)
								(= local107 1)
								(= local82 (+ (= local80 local104) 5))
								(= local81 15)
								(self setScript: faeryTalk)
							else
								(HighPrint 70 4)
								;They seem to be ignoring you.
							)
						else
							(HighPrint 70 5)
							;Who are you talking to?
						)
					)
					((Said 'ask>')
						(if (and faeriesOnScreen (== local4 0))
							(= local109 1)
							(cond 
								((Said '//mushroom,toadstool,ring')
									(AddChaseScript)
									(= local107 1)
									(= local82 (+ (= local80 local100) 8))
									(= local81 11)
									(self setScript: faeryTalk)
								)
								((Said '//dust[<faerie,about]')
									(if (or askedForDust (Btst POINTS_GETFAIRYDUST))
										(HighPrint 70 6)
										;You know all about it, now.
									else
										(AddChaseScript)
										(= local107 1)
										(= local82 (+ (= local80 local98) 22))
										(= local81 9)
										(self setScript: faeryTalk)
									)
								)
								((Said '//faerie,magic')
									(AddChaseScript)
									(= local107 1)
									(= local82 (+ (= local80 local101) 7))
									(= local81 12)
									(self setScript: faeryTalk)
								)
								((Said '//forest')
									(AddChaseScript)
									(= local107 1)
									(= local82 (+ (= local80 local102) 6))
									(= local81 13)
									(self setScript: faeryTalk)
								)
								((Said '//dryad')
									(AddChaseScript)
									(= local107 1)
									(= local82 (+ (= local80 local103) 6))
									(= local81 14)
									(self setScript: faeryTalk)
								)
								(else
									(event claimed: TRUE)
									(= local109 0)
									(if (Btst GOT_FAIRIES_ATTENTION)
										(= local107 1)
										(= local82 (+ (= local80 local104) 5))
										(= local81 15)
										(self setScript: faeryTalk)
									else
										(HighPrint 70 4)
										;They seem to be ignoring you.
									)
								)
							)
							(if local109 (SolvePuzzle POINTS_TALKTOFAIRIES 1))
						else
							(event claimed: FALSE)
						)
					)
					((Said 'cast>')
						(switch (= spell (SaidSpell event))
							(DETMAGIC
								(if (CastSpell spell)
									(if faeriesOnScreen
										(HighPrint 70 7)
										;There is much magic surrounding the fairies and their ring of mushrooms.
									else
										(HighPrint 70 8)
										;You detect faint emanations of magic near the ring of mushrooms.
									)
								)
							)
							(DAZZLE
								(if (CastSpell spell) (CastDazz) (FaeriesHostile))
							)
							(CALM
								(if (CastSpell spell) (CastCalm))
							)
							(FLAMEDART
								(if (CastSpell spell)
									(FlameCast 0)
									(FaeriesHostile)
								)
							)
							(else  (event claimed: FALSE))
						)
					)
					((Said 'throw/dagger,dagger')
						(if (ego has: iDagger)
							(KnifeCast 0)
							(FaeriesHostile)
						else
							(HighPrint 70 9)
							;You don't have a dagger.
						)
					)
					((Said 'lockpick,get,gather/mushroom')
						(if (and (== local4 0) (== (ego script?) 0))
							(= eatingShrooms FALSE)
							(ego setScript: pickEm)
						else
							(HighPrint 70 1)
							;Don't you want to dance?
						)
					)
					((Said 'eat/mushroom')
						(cond 
							((Btst fAteShrooms) (event claimed: FALSE))
							((and (== local4 0) (== (ego script?) 0)) (Bset fAteShrooms) (ego setScript: eatShroom))
							(else
								(HighPrint 70 1)
								;Don't you want to dance?
							)
						)
					)
					((Said 'get>')
						(if (== local4 0)
							(cond 
								((Said '/faerie')
									(if faeriesOnScreen
										(HighPrint 70 10)
										;The fairies can avoid your grasp easily.
									else
										(HighPrint 70 11)
										;Huh?
									)
								)
								((Said '/dust[<faerie]')
									(cond 
										((Btst POINTS_GETFAIRYDUST)
											(HighPrint 70 12)
											;Don't be greedy.  We already gave you some.
											)
										(askedForDust
											(HighPrint 70 13)
											;Perhaps you should be better prepared to get some fairy dust next time.
											)
										((not faeriesOnScreen)
											(HighPrint 70 14)
											;Where could you possibly get that?
											)
										((not local3)
											(HighPrint 70 15)
											;Maybe you should ask the Fairies for some.  That would be the polite thing to do.
											)
										(else
											(= local107 1)
											(= local82 (+ (= local80 local98) 22))
											(= local81 9)
											(self setScript: faeryTalk)
										)
									)
								)
							)
						else
							(HighPrint 70 1)
							;Don't you want to dance?
							(event claimed: TRUE)
						)
					)
					((Said 'look>')
						(cond 
							(
							(Said '[<at,around][/!*,forest,greenery,clearing]')
							(HighPrint 70 16)
							;The trees look more vibrant than most of the forest.
							(HighPrint 70 17)
							;There is a ring of mushrooms on the northwest side of the clearing.
							)
							((Said '/ring,mushroom,toadstool')
								(if faeriesOnScreen
									(HighPrint 70 18)
									;The fairy ring is luminous at night.
								else
									(HighPrint 70 19)
									;The ring of mushrooms contains mushrooms slightly larger than the ones you are used to.
								)
							)
							((Said '/faerie,creature,chandelier')
								(if faeriesOnScreen
									(HighPrint 70 20)
									;The fairies look like little dancing lights, but you get a strong sense that they are female with butterfly wings.
								else
									(HighPrint 70 21)
									;There aren't any of those around.
								)
							)
							((Said '/south')
								(HighPrint 70 22)
								;The trees seem more dense and lush than the rest of the woods.
								)
							((Said '/tree')
								(HighPrint 70 16)
								;The trees look more vibrant than most of the forest.
								)
							((Said '/east,north')
								(HighPrint 70 23)
								;You see trees and brush.
								)
							((Said '/west')
								(HighPrint 70 24)
								;The trees here look thicker and healthier than in other parts of the forest.
							)
						)
					)
				)
			)
		)
	)
)

(instance aFaeryScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (> local69 0)
					(client
						setMotion:
							MoveTo
							(Random
								(- [local70 register] 25)
								(+ [local70 register] 25)
							)
							(Random
								(- [local75 register] 25)
								(+ [local75 register] 25)
							)
							self
					)
				else
					(client
						setMotion:
							MoveTo
							(Random
								(- [faeryX register] 15)
								(+ [faeryX register] 15)
							)
							(Random
								(- [faeryY register] 15)
								(+ [faeryY register] 15)
							)
							self
					)
				)
			)
			(1 (self changeState: 0))
		)
	)
)

(instance aChaseScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					(local108
						(client
							setMotion:
								MoveTo
								(- (Random (ego x?) (+ (ego x?) 8)) 18)
								(- (Random (ego y?) (+ (ego y?) 8)) 16)
								self
						)
					)
					((Btst DANCING_FOR_FAIRIES)
						(client
							setMotion:
								MoveTo
								(- (Random (ego x?) (+ (ego x?) 20)) 10)
								(- (Random (ego y?) (+ (ego y?) 30)) 15)
								self
						)
					)
					(else
						(client
							setMotion:
								MoveTo
								(- (Random (ego x?) (+ (ego x?) 80)) 40)
								(- (Random (ego y?) (+ (ego y?) 30)) 15)
								self
						)
					)
				)
			)
			(1 (self changeState: 0))
		)
	)
)

(instance faeryTalk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not isHandsOff) (HandsOff))
				(= seconds (if local107 4 else 3))
				(FairySays seconds 296 local80)
			)
			(1
				(= local107 0)
				(= seconds 0)
				(++ local80)
				(cond 
					(
						(or
							(and local87 (== local80 (+ local96 7)))
							(and (== local81 3) (== local80 (+ local92 7)))
							(and (== local81 16) (== local80 (+ local105 7)))
						)
						(ego setScript: deathDance)
						(self changeState: 0)
					)
					((< local80 local82) (self changeState: 0))
					(else
						(switch local81
							(1
								(Bset DANCED_FOR_FAIRIES)
								(= local4 100)
								(HandsOn)
							)
							(17 (= local4 100) (HandsOn))
							(2 (ego setScript: cuteDance))
							(5 (= local84 1))
							(4 (= local84 1))
							(8
								(= local69 0)
								(AddChaseScript)
							)
							(9 (ego setScript: getDust))
							(10 (getDust cue:))
							(15 (AddFaeryDanceScript))
						)
						(if (== (ego script?) 0) (cls) (HandsOn))
						(self dispose:)
					)
				)
			)
		)
	)
)

(instance cuteDance of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(SolvePuzzle POINTS_DANCEWITHFAIRIES 3)
				(AddFaeryDanceScript)
				(ego
					illegalBits: 0
					ignoreActors:
					setMotion: MoveTo local85 local86 self
				)
			)
			(1
				(if (TrySkill AGIL tryFaeryDance)
					(self cue:)
				else
					(ego setScript: klutzDance)
					(egoBoogie play:)
				)
			)
			(2
				(ego
					view: vEgoDance
					setLoop: 6
					cel: 0
					cycleSpeed: 1
					moveSpeed: 3
					setCycle: Forward
				)
				(self cue:)
			)
			(3
				(++ local83)
				(ego
					setMotion:
						MoveTo
						(Random (- local85 20) (+ local85 20))
						(Random (- local86 10) (+ local86 10))
						self
				)
			)
			(4
				(cond 
					((== local83 2) 
						(Bset DANCING_FOR_FAIRIES) 
						(AddChaseScript) 
						(self changeState: 3)
					)
					((== local83 3)
						(= local107 1)
						(= local82 (+ (= local80 local94) 9))
						(= local81 5)
						(rm70 setScript: faeryTalk)
						(self changeState: 3)
					)
					(local84 (= local84 0) (self cue:))
					((>= local83 40) (self changeState: 13))
					(else (self changeState: 3))
				)
			)
			(5
				(AddFaeryDanceScript)
				(ego setLoop: 5 cel: 0 setCycle: 0)
				(= seconds 2)
			)
			(6
				(ego setCycle: EndLoop)
				(= cycles 10)
			)
			(7
				(ego setCycle: BegLoop)
				(= cycles 10)
			)
			(8
				(ego x: (+ (ego x?) 12) setLoop: 4 cel: 0 setCycle: EndLoop)
				(= cycles 10)
			)
			(9
				(FairySays 2 {Wow!})
				(= seconds 2)
			)
			(10 (ego setCycle: BegLoop self))
			(11
				(NormalEgo)
				(ego x: (- (ego x?) 12) loop: 2)
				(= cycles 5)
			)
			(12
				(Bclr DANCING_FOR_FAIRIES)
				(AddChaseScript)
				(= local107 1)
				(= local82 (+ (= local80 local95) 11))
				(= local81 6)
				(rm70 setScript: faeryTalk)
				(HandsOn)
				(self dispose:)
			)
			(13
				(HighPrint 70 25)
				;I'll bet you're sorry you said that.
				(= local83 0)
				(HandsOn)
				(NormalEgo)
				(ego x: (- (ego x?) 12) loop: 2)
				(self dispose:)
			)
		)
	)
)

(instance klutzDance of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					view: vEgoDance
					setLoop: (Random 0 4)
					cel: 0
					cycleSpeed: (if local87 0 else 1)
					setCycle: EndLoop self
				)
				(++ local83)
			)
			(1
				(cond 
					((== local83 5)
						(= local107 1)
						(= local82 (+ (= local80 local93) 5))
						(= local81 4)
						(rm70 setScript: faeryTalk)
						(self changeState: 0)
					)
					(local84 (= local84 0) (egoBoogie stop:) (self cue:))
					((>= local83 40) (self changeState: 4))
					(else (self changeState: 0))
				)
			)
			(2
				(HandsOn)
				(NormalEgo)
				(ego x: (- (ego x?) 12) loop: 2)
				(= cycles 10)
			)
			(3
				(AddChaseScript)
				(= local107 1)
				(= local82 (+ (= local80 local95) 11))
				(= local81 6)
				(rm70 setScript: faeryTalk)
				(self dispose:)
			)
			(4
				(HighPrint 70 25)
				(= local83 0)
				(HandsOn)
				(NormalEgo)
				(ego x: (- (ego x?) 12) loop: 2)
				(self dispose:)
			)
		)
	)
)

(instance deathDance of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= local83 0)
				(ego
					illegalBits: 0
					ignoreActors:
					setMotion: MoveTo 94 102 self
				)
			)
			(1
				(faeryMusic stop:)
				(bopTilYouDrop play:)
				(ego view: vEgoDance x: (+ (ego x?) 12))
				(self cue:)
			)
			(2
				(ego setLoop: (Random 0 4) cel: 0 setCycle: EndLoop self)
				(++ local83)
			)
			(3
				(cond 
					((== (mod local83 3) 0) (FairySays 2 {Dance!}) (self changeState: 2))
					((== local83 19) (self cue:))
					(else (self changeState: 2))
				)
			)
			(4
				(bopTilYouDrop stop:)
				(ego setLoop: 7 setCel: 1)
				(= cycles 10)
			)
			(5
				(ego cycleSpeed: 1 setCycle: EndLoop)
				(= cycles 35)
			)
			(6
				(FairySays 4 {He's no fun!__He fell right over!})
				(= seconds 4)
			)
			(7
				(EgoDead 70 26
					#title {Land of 1000 Dances}
					#icon vEgoDance 2 5)
					;For such wimpy-looking creatures, those fairies sure can play rough!
					;You never danced so hard in your entire life, which is now over.
			)
		)
	)
)

(instance pickEm of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (> (ego x?) 92)
					(ego illegalBits: 0 setMotion: MoveTo 175 109 self)
				else
					(ego illegalBits: 0 setMotion: MoveTo 19 109 self)
				)
			)
			(1
				(ego
					view: vEgoThrowing
					setLoop: (if (< (ego x?) 100) 0 else 1)
					cel: 0
					setCycle: EndLoop self
				)
			)
			(2
				(if eatingShrooms
					(TimePrint 4 70 27)
					;You eat a few of the lovely mushrooms.
				else
					(HighPrint 70 28)
					;You pick a handful of the smaller mushrooms and carefully put them away in your backpack.
					(ego get: iMushroom 3)
					(Bset fHaveFaeryShrooms)
					(SolvePuzzle POINTS_PICKMUSHROOMS 3)
				)
				(ego setCycle: BegLoop self)
			)
			(3
				(NormalEgo)
				(ego loop: 2)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance getDust of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= askedForDust 1)
				(ego view: vEgoGetFaeryDust loop: 0 cel: 0 setCycle: CycleTo 3 1 self)
			)
			(1
				(TimePrint 5 70 29)
				;You hold out your hand...what else could you do?
				(= local108 1)
				(= seconds 6)
			)
			(2
				(= local81 10)
				(= local107 1)
				(= local82 (+ (= local80 local99) 3))
				(rm70 setScript: faeryTalk)
			)
			(3
				(= cycles 10)
				(= local108 0)
			)
			(4
				(cond 
					((ego has: iFlask)
						(TimePrint 8 70 30)
						;You place the dust carefully away in an empty flask.
						(ego use: iFlask 1)
						(ego get: iFairyDust)
						(SolvePuzzle POINTS_GETFAIRYDUST 8)
					)
					(
						(or
							(ego has: iHealingPotion)
							(ego has: iManaPotion)
							(ego has: iStaminaPotion)
							(ego has: iGhostOil)
						)
						(TimePrint 8 70 31)
						;You realize that you need something such as an empty flask to put this fairy dust into.
					)
					(else
						(TimePrint 8 70 32)
						;As the fairy dust sifts through your fingers, you realize that you needed something to put it into.
						)
				)
				(= seconds 8)
			)
			(5
				(ego setCycle: EndLoop)
				(= cycles 15)
			)
			(6
				(NormalEgo)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance eatShroom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= eatingShrooms TRUE)
				(self setScript: pickEm self)
			)
			(1
				(HandsOff)
				(= mushroomPalette currentPalette)
				(= currentPalette 2)
				(DrawPic (curRoom curPic?) PLAIN TRUE currentPalette)
				(Animate (cast elements?) FALSE)
				(= seconds 3)
			)
			(2
				(HighPrint 70 33)
				;Wow!  That was pretty wild!  It's probably not a good idea to eat too many more of these mushrooms, though.
				(= local111 1)
				(= currentPalette mushroomPalette)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)
