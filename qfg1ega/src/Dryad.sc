;;; Sierra Script 1.0 - (do not remove this comment)
(script# 76)
(include game.sh)
(use Main)
(use Sleep)
(use ThrowFlameDart)
(use ThrowDagger1)
(use ThrowRock)
(use CastDazz)
(use Target)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use Invent)
(use User)
(use Actor)
(use System)

(public
	rm76 0
)

(enum 1 ;Dryad states
	dryadAvailable
	dryadHere
	dryadOut
)


(local
	dryadState
	magicAcornOnGround
	eatOffGround
	dryadHostile
	attackedStag
	local5
	castFlame
)
(procedure (PickUpAcorn)
	(if (and magicAcornOnGround (< (ego distanceTo: acorn) 80))
		(HighPrint 76 0)
		;You pick up the Dryad's gift of a Magic Acorn.
	else
		(HighPrint 76 1)
		;You find an ordinary acorn on the ground.
	)
	(ego setScript: pickEmUp)
)

(procedure (EatAcorn)
	(HighPrint 76 2)
	;That acorn tasted awfully bitter...
	;just like an ordinary acorn.
)

(procedure (SayNo)
	(HandsOff)
	(if (Btst DRYAD_AGREED_HELP)
		(HighPrint 76 3)
		;"Until you have done so, you are only intruding on my concentration."
	else
		(HighPrint 76 4)
		;"You need to become a friend of the forest.  Harm not the gentle creatures or plants.
		;See with all your senses the magic of the trees and wild places."
		(HighPrint 76 5)
		;"Wander and learn.  Then you too will be in harmony with the forest."
	)
	(dryad setScript: intoTree)
)

(instance miscSound of Sound
	(properties
		number 97
		priority 1
		loop 2
	)
)

(instance spitSound of Sound
	(properties
		number 18
		priority 1
	)
)

(instance gulpSound of Sound
	(properties
		number 27
		priority 1
	)
)

(instance bush of View
	(properties
		y 141
		x 80
		view vDryadRoom
	)
)

(instance acorn of Prop
	(properties
		y 132
		x 130
		view vDryadRoom
		loop 1
	)
)

(instance stag of TargActor
	(properties
		view vStag
	)
	
	(method (getHurt)
		(= missedDaggers (+ missedDaggers hitDaggers))
		(= hitDaggers 0)
		(HighPrint 76 6)
		;The stag looks more surprised than hurt.
	)
)

(instance seed of Actor
	(properties
		view vSpittingSpirea
	)
)

(instance dryad of Actor
	(properties
		y 116
		x 96
		view vDryad
		illegalBits $0000
	)
)

(instance rm76 of Room
	(properties
		picture 76
		style DISSOLVE
	)
	
	(method (init)
		(if (not Night)
			(LoadMany VIEW vDryad vEgoKilledByDryad)
			(if (or (Btst SMASHED_FLOWER1) (Btst SMASHED_FLOWER2) (Btst SMASHED_FLOWER3))
				(Load VIEW vEgoCatchSeed)
				(LoadMany SOUND (SoundFX 18) (SoundFX 27))
			)
		else
			(Load SCRIPT 7)
			(Load VIEW vEgoCatchSeed)
		)
		(LoadMany VIEW vDryadRoom vEgoGetFaeryDust vEgoThrowing)
		(LoadMany SOUND 97 98)
		(if (Btst STAG_PRESENT) (Load VIEW vStag))
		(super init:)
		(bush ignoreActors: init: stopUpd: addToPic:)
		(StatusLine enable:)
		(NormalEgo)
		(ChangeGait MOVE_WALK 0)
		(= yesNoTimer 0)
		(if (not Night)
			(spitSound number: (SoundFX 18) init:)
			(gulpSound number: (SoundFX 27) init:)
			(seed
				illegalBits: 0
				setLoop: 4
				init:
				ignoreActors:
				setCycle: Forward
				stopUpd:
				hide:
			)
		)
		(if (and (not Night) (Btst STAG_PRESENT))
			(stag
				view: vStag
				x: 318
				y: 145
				setScript: stagEntrance
				init:
				illegalBits: 0
			)
		else
			(ego posn: 318 130 init: setMotion: MoveTo 290 130)
			(Bclr STAG_PRESENT)
			(= dryadState dryadAvailable)
		)
		(miscSound init: play:)
	)
	
	(method (doit)
		(cond 
			(
			(and attackedStag (not (ego script?)) (< (stag x?) 50))
				(= attackedStag 0)
				(HighPrint 76 7)
				;The stag is startled by your action.
				(stag setScript: stagBolts)
			)
			(
				(and
					dryadHostile
					(not (ego script?))
					(not (stag script?))
					(not (dryad script?))
					(== dryadState dryadHere)
				)
				(= dryadHostile FALSE)
				(dryad setScript: egoToStag)
			)
		)
		(cond 
			((> yesNoTimer 1) (-- yesNoTimer))
			((== yesNoTimer 1) (= yesNoTimer 0) (SayNo))
		)
		(cond 
			((and (== (ego edgeHit?) 2) (>= dryadState 1)) (Bclr STAG_PRESENT) (curRoom newRoom: 77))
			(
				(and
					(< (ego x?) 200)
					(== dryadState dryadAvailable)
					(not Night)
					(or
						castFlame
						(not (Btst DISPEL_LEARNED_RECIPE))
						(Btst STAG_HURT)
						(Btst SMASHED_FLOWER1)
						(Btst SMASHED_FLOWER2)
						(Btst SMASHED_FLOWER3)
					)
				)
				(= dryadState dryadHere)
				(dryad init: setScript: outOfTree)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(Bset VISITED_DRYAD)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp spell temp1)
		(switch (event type?)
			(saidEvent
				(if (> yesNoTimer 0)
					(cond 
						((Said 'affirmative,please')
							(= yesNoTimer 0)
							(HandsOff)
							(if (Btst DRYAD_AGREED_HELP)
								(HighPrint 76 8)
								;"Give the seed to me."
								(dryad x: (+ (dryad x?) 6) setLoop: 4 setCycle: EndLoop)
								(if (ego has: iSeed)
									(dryad setScript: hasSeed)
								else
									(HighPrint 76 9)
									;You are forced to confess that you lied to the Dryad.  You don't have the magic seed.
									(HighPrint 76 10)
									;She tells you:  "It's not nice to fool Mother Nature."
									(dryad setScript: intoTree)
								)
							else
								(SolvePuzzle POINTS_AGREETOHELPDRYAD 1)
								(HighPrint 76 11)
								;"Then you shall aid me, and I shall aid you in your quest."
								(HighPrint 76 12)
								;"Bring me a seed from the Spore Spitting Spirea of the North that I may plant it
								;elsewhere in order to preserve these rare and magical plants."
								(HighPrint 76 13)
								;"Thus will you become a true friend of the forest."
								(Bset DRYAD_AGREED_HELP)
								(if (ego has: iSeed)
									(HighPrint 76 14)
									;"I detect that you have in your possession such a seed.  Are you willing to give me the seed?"
									(= yesNoTimer 150)
									(User canInput: TRUE)
								else
									(dryad setScript: intoTree)
								)
							)
						)
						((Said 'n') (= yesNoTimer 0) (SayNo))
						(else (event claimed: TRUE)
							(HighPrint 76 15)
							;"Please.  Just answer my question."
							)
					)
				)
				(cond 
					((super handleEvent: event))
					((or (Said 'nap') (Said 'go[<to]/nap'))
						(if (not (NeedSleep))
							(HighPrint 76 16)
							;You just can't sleep during the daytime.
							(DisposeScript SLEEP)
						else
							(ego setScript: goToSleep)
						)
					)
					((Said 'cast>')
						(switch (= spell (SaidSpell event))
							(DETMAGIC
								(if (CastSpell spell)
									(if (== dryadState dryadHere)
										(HighPrint 76 17)
										;The Dryad radiates magic.
									else
										(HighPrint 76 18)
										;The oak tree radiates magic.
									)
								)
							)
							(DAZZLE
								(if (CastSpell spell)
									(if (and (Btst STAG_PRESENT) (< (stag x?) 50)) (= attackedStag TRUE))
									(if (== dryadState dryadHere) (= dryadHostile TRUE))
									(CastDazz)
								)
							)
							(FLAMEDART
								(if (CastSpell spell)
									(if (== dryadState dryadHere) (= dryadHostile TRUE))
									(if (Btst STAG_PRESENT) (= attackedStag TRUE))
									(if (cast contains: stag)
										(Face ego stag)
										(RedrawCast)
									)
									(if
									(and (cast contains: stag) (not (ego script?)))
										(FlameCast stag)
									else
										(FlameCast 0)
									)
									(= castFlame TRUE)
								)
							)
							(else  (event claimed: FALSE))
						)
					)
					((Said 'throw/dagger,dagger')
						(= temp1 (if (cast contains: stag) stag else 0))
						(if (KnifeCast temp1)
							(if (== dryadState dryadHere) (= dryadHostile TRUE))
							(if (Btst STAG_PRESENT) (= attackedStag TRUE) (Bset STAG_HURT))
							(if (cast contains: stag)
								(Face ego stag)
								(RedrawCast)
							)
						)
					)
					((Said 'throw/boulder')
						(= temp1 (if (cast contains: stag) stag else 0))
						(if (RockCast temp1)
							(if (== dryadState dryadHere) (= dryadHostile TRUE))
							(if (Btst STAG_PRESENT) (= attackedStag TRUE) (Bset STAG_HURT))
							(if (cast contains: stag)
								(Face ego stag)
								(RedrawCast)
							)
						)
					)
					((Said 'eat/acorn,nut')
						(if (ego has: iAcorn)
							(EatAcorn)
							(ego use: iAcorn)
							(SolvePuzzle POINTS_EATACORN -5)
						else
							(= eatOffGround TRUE)
							(PickUpAcorn)
						)
					)
					((Said 'climb,ride/buck')
						(if (Btst STAG_PRESENT)
							(HighPrint 76 19)
							;He's beyond your reach.
						else
							(HighPrint 76 20)
							;Huh?
						)
					)
					((Said 'climb>')
						(cond 
							((Said '/tree,oak')
								(HighPrint 76 21)
								;Something about the old oak tree makes you reluctant to climb upon it.
								)
							((Said '/boulder')
								(HighPrint 76 22)
								;The rocks to the north look too steep.
								)
						)
					)
					((Said 'ask//date')
						(HighPrint 76 23)
						;"Perhaps the farmer has dates in his field."
						)
					((Said 'chat/dryad')
						(if 2
							(HighPrint 76 24)
							;"I beg your pardon?"
						else
							(HighPrint 76 25)
							;Does a Dryad bark?
							(HighPrint 76 26)
							;No, but she's a real birch.
							(HighPrint 76 27)
							;Don't make an ash of yourself.
						)
					)
					((Said 'lockpick<up/acorn,nut') (PickUpAcorn))
					((Said 'burn,torch/tree,oak')
						(if (== dryadState dryadHere)
							(HighPrint 76 28)
							;As you begin to form thoughts of violence...
							(dryad setScript: egoToStag)
						else
							(HighPrint 76 29)
							;You think better of burning up the woods.
						)
					)
					((Said 'fight,kill,beat,chop>')
						(cond 
							((Said '/buck')
								(if (Btst STAG_PRESENT)
									(= attackedStag TRUE)
									(if (== dryadState dryadHere)
										(HighPrint 76 28)
										;As you begin to form thoughts of violence...
										(dryad setScript: egoToStag)
									else
										(Bset STAG_HURT)
									)
								else
									(HighPrint 76 19)
									;He's beyond your reach.
									(Bset STAG_HURT)
								)
							)
							((Said '/dryad,girl,female')
								(if (== dryadState dryadHere)
									(HighPrint 76 28)
									;As you begin to form thoughts of violence...
									(dryad setScript: egoToStag)
								else
									(HighPrint 76 30)
									;She's beyond your reach.
								)
							)
							((Said '/tree,oak')
								(if (== dryadState dryadHere)
									(HighPrint 76 28)
									;As you begin to form thoughts of violence...
									(dryad setScript: egoToStag)
								else
									(HighPrint 76 31)
									;You needn't dull your blade playing woodsman.
								)
							)
						)
					)
					((Said 'get>')
						(cond 
							((Said '/acorn,nut') (PickUpAcorn))
							((Said '/dryad,girl,female')
								(if (== dryadState dryadHere)
									(HighPrint 76 32)
									;She's rooted to the ground.
								else
									(event claimed: FALSE)
								)
							)
							((Said '/buck')
								(if (Btst STAG_PRESENT)
									(HighPrint 76 33)
									;The stag remains out of your reach.
								else
									(event claimed: FALSE)
								)
							)
						)
					)
					((Said 'look>')
						(cond 
							(
							(Said '[<at,around][/!*,forest,greenery,clearing]')
							(HighPrint 76 34)
							;You are in a strange and beautiful part of the forest.  There is something special about this place.
							(HighPrint 76 35)
							;The large, gnarled oak seems to draw your attention.
							)
							((Said '/dryad,girl,female')
								(if (== dryadState dryadHere)
									(HighPrint 76 36)
									;The Dryad appears to be a woman made of wood and leaves.
								else
									(HighPrint 76 37)
									;You cannot look at what you cannot see.
								)
							)
							((Said '/tree,oak')
								(HighPrint 76 35)
								;The large, gnarled oak seems to draw your attention.
								)
							((Said '/boulder')
								(HighPrint 76 38)
								;There is a rock wall northwest of the large oak tree.
								)
							((Said '/buck')
								(if (Btst STAG_PRESENT)
									(HighPrint 76 39)
									;The majestic white stag is an outstanding example of the beauty of the animal kingdom.
								else
									(HighPrint 76 40)
									;You see no such animal.
								)
							)
							((Said '/acorn,nut') (if magicAcornOnGround
									(HighPrint 76 41)
									;There is a large, slightly glowing acorn on the ground.
									else
									(HighPrint 76 42)
									;There are acorns here and there on the ground.
									)
								)
							((Said '/west')
								(HighPrint 76 43)
								;The forest becomes so lush and thick that it is difficult to see far.
								)
							((Said '/south')
								(HighPrint 76 44)
								;The brush and undergrowth has formed an impassable thicket.
								)
							((Said '/north')
								(HighPrint 76 45)
								;You see thick brush, rocks, and heavy woods to the north.  A large oak tree dominates the view.
								)
							((Said '/east')
								(HighPrint 76 46)
								;You see a clearing back the way you came.
								)
							((Said '/bush')
								(HighPrint 76 47)
								;Most of the bushes around here seem to be bramble and are very thorny.
								)
						)
					)
				)
			)
		)
	)
)

(instance stagEntrance of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(stag
					setLoop: 1
					cel: 0
					cycleSpeed: 1
					xStep: 5
					moveSpeed: 1
					posn: 319 150
					setCycle: Forward
					setMotion: MoveTo 200 140 self
				)
			)
			(1
				(stag setLoop: 5 cel: 0 setCycle: EndLoop self)
			)
			(2
				(stag setLoop: 7 cycleSpeed: 3 setCycle: Forward)
				(= cycles 30)
			)
			(3 (stag setCycle: EndLoop self))
			(4
				(stag setLoop: 5 cel: 7 cycleSpeed: 1 setCycle: BegLoop self)
			)
			(5
				(stag
					setLoop: 1
					cel: 0
					setCycle: Forward
					setMotion: MoveTo 130 140 self
				)
				(ego posn: 300 130 init: setMotion: MoveTo 290 130)
				(= dryadState 1)
			)
			(6 (= cycles 1))
			(7
				(if (not (Btst VISITED_DRYAD))
					(HighPrint 76 48)
					;You follow the stag into this forest corner.  You feel as though the eyes of the forest are watching you.
				else
					(HighPrint 76 49)
					;Again, you feel a sense of closeness with nature.
				)
				(stag
					setLoop: 9
					cel: 0
					xStep: 9
					setCycle: EndLoop
					setMotion: MoveTo 50 140 self
				)
			)
			(8
				(stag
					setLoop: 1
					cel: 0
					xStep: 5
					cycleSpeed: 1
					setCycle: Walk
					setMotion: MoveTo 30 140 self
				)
			)
			(9
				(if (not (Btst VISITED_DRYAD))
					(HighPrint 76 50)
					;You watch the stag, fascinated with his grace and beauty.  There is something special about this place.
				else
					(HighPrint 76 51)
					;What a beautiful animal!
				)
				(stag setLoop: 3 cycleSpeed: 1 setCycle: EndLoop self)
			)
			(10 (HandsOn) (self cue:))
			(11
				(stag setLoop: 4 cel: 0 cycleSpeed: 1 setCycle: EndLoop self)
			)
			(12
				(stag loop: 6 cycleSpeed: 3 setCycle: Forward)
				(= cycles (Random 20 90))
			)
			(13 (stag setCycle: EndLoop self))
			(14
				(stag loop: 4 cel: 7 cycleSpeed: 1 setCycle: BegLoop self)
			)
			(15 (= cycles (Random 5 35)))
			(16 (self changeState: 11))
		)
	)
)

(instance outOfTree of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(miscSound stop: number: 98 play:)
				(dryad cycleSpeed: 1 setCycle: EndLoop)
				(= cycles 25)
			)
			(1
				(dryad setLoop: 1 cel: 0 setCycle: CycleTo 2 1 self)
			)
			(2
				(dryad
					moveSpeed: 1
					setStep: 5 3
					setCycle: EndLoop
					setMotion: MoveTo 124 130 self
				)
			)
			(3
				(cond 
					((or castFlame (Btst STAG_HURT)) (dryad setLoop: 6 cel: 6 forceUpd: setScript: egoToStag))
					((Btst DRYAD_AGREED_HELP)
						(HighPrint 76 52)
						;"Have you brought the seed I requested?"
						(= yesNoTimer 150)
						(User canInput: TRUE)
					)
					((or (Btst SMASHED_FLOWER1) (Btst SMASHED_FLOWER2) (Btst SMASHED_FLOWER3)) (dryad setLoop: 6 cel: 6 forceUpd: setScript: egoToPlant))
					((Btst MET_DRYAD)
						(HighPrint 76 53)
						;"Well, Hero-to-be!  Are you yet a friend of the woods?"
						(= yesNoTimer 150)
						(User canInput: TRUE)
					)
					(else
						(HighPrint 76 54)
						;"I am the Dryad, keeper of the woods.  Are you one with the woods?"
						(= yesNoTimer 150)
						(User canInput: TRUE)
					)
				)
				(Bset MET_DRYAD)
			)
		)
	)
)

(instance intoTree of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(dryad
					setLoop: 1
					cel: 9
					moveSpeed: 1
					setStep: 5 3
					setCycle: CycleTo 2 -1
					setMotion: MoveTo 96 116 self
				)
			)
			(1 (dryad setCycle: BegLoop self))
			(2
				(dryad cycleSpeed: 1 setLoop: 0 cel: 5 setCycle: BegLoop self)
			)
			(3
				(if (Btst DISPEL_LEARNED_RECIPE)
					(acorn init: setCycle: EndLoop)
					(= magicAcornOnGround TRUE)
					(HighPrint 76 55)
					;You see an acorn fall slowly to the ground.
				)
				(HandsOn)
				(= dryadState dryadOut)
				(dryad setScript: 0)
			)
		)
	)
)

(instance hasSeed of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego ignoreActors: setMotion: MoveTo 154 132 self)
			)
			(1
				(ego setLoop: 3)
				(= cycles 2)
			)
			(2
				(ego view: vEgoGetFaeryDust setLoop: 0 cel: 1 forceUpd:)
				(= cycles 15)
			)
			(3
				(ego cycleSpeed: 1 setCycle: CycleTo 3 1)
				(= cycles 10)
			)
			(4
				(HighPrint 76 56)
				;You drop the seed into the Dryad's limbs.
				(SolvePuzzle POINTS_GIVESEED 7)
				(ego use: 20 setCycle: EndLoop self)
			)
			(5
				(if (or (Btst SMASHED_FLOWER1) (Btst SMASHED_FLOWER2) (Btst SMASHED_FLOWER3))
					(HighPrint 76 57)
					;You tell the Dryad:  "No amount of persuasion could convince the flowers to relinquish their seed easily,
					;so I was forced to hack my way to the seed with my weapon."
					(dryad setScript: oMyGod)
				else
					(NormalEgo)
					(ego loop: 1 forceUpd:)
					(dryad setCycle: BegLoop self)
				)
			)
			(6
				(HighPrint 76 58)
				;"Now this will boldly grow where none has grown before!"
				(self cue:)
			)
			(7
				(HighPrint 76 59)
				;"Heed now my words, friend of the forest, and heed them well."
				(HighPrint 76 60)
				;"Friend, you must know that there is an evil in this valley which perverts the ways of nature.
				;The prophecy says that a hero will bring a young human from out this darkness."
				(HighPrint 76 61)
				;"If you are the one to accomplish this task, there is a potion to break enchantments which you must have the Healer make."
				(HighPrint 76 62)
				;"You must gather these ingredients:"
				(HighPrint 76 63)
				;"Flowers from Erana's Peace,"
				(HighPrint 76 64)
				;"Green Fur,"
				(HighPrint 76 65)
				;"Fairy Dust,"
				(HighPrint 76 66)
				;"a Magic Acorn,"
				(HighPrint 76 67)
				;"and Flying Water."
				(HighPrint 76 68)
				;"Farewell, friend.  I must return to my concentration.  May the forest forever surround you."
				(Bset DISPEL_LEARNED_RECIPE)
				(Bclr DRYAD_AGREED_HELP)
				(dryad setScript: intoTree)
			)
		)
	)
)

(instance pickEmUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (and magicAcornOnGround (< (ego distanceTo: acorn) 80))
					(ego setMotion: MoveTo 147 135 self)
				else
					(self cue:)
				)
			)
			(1
				(ego
					view: vEgoThrowing
					setLoop: 1
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(2
				(cond 
					((and magicAcornOnGround (< (ego distanceTo: acorn) 80))
						(= magicAcornOnGround FALSE)
						(ego get: iAcorn)
						(SolvePuzzle POINTS_GETACORN 1)
						(acorn dispose:)
					)
					(eatOffGround
						(HighPrint 76 69)
						;You pick it up and eat it.
						)
					(else
						(HighPrint 76 70)
						;The acorn appears too ordinary.  You drop it back on the ground.
						)
				)
				(ego setCycle: BegLoop self)
			)
			(3
				(if eatOffGround (= eatOffGround FALSE) (EatAcorn))
				(NormalEgo)
				(ego setScript: 0)
				(HandsOn)
			)
		)
	)
)

(instance stagBolts of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(stag setLoop: 4 cel: 7 cycleSpeed: 0 setCycle: BegLoop self)
			)
			(1
				(stag setLoop: 2 cel: 0 setCycle: EndLoop self)
			)
			(2
				(stag
					setLoop: 9
					cel: 0
					xStep: 9
					setCycle: EndLoop
					setMotion: MoveTo -20 140 self
				)
			)
			(3 (Bclr STAG_PRESENT) (stag dispose:))
		)
	)
)

(instance oMyGod of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(dryad setLoop: 2 cel: 0 forceUpd:)
				(= cycles 10)
			)
			(1
				(HighPrint 76 71)
				;"You did WHAT???"
				(self cue:)
			)
			(2
				(dryad setCycle: CycleTo 3 1)
				(= seconds 3)
			)
			(3 (dryad setCycle: EndLoop self))
			(4
				(dryad setScript: egoToPlant)
			)
		)
	)
)

(instance egoToPlant of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(stag setScript: stagBolts)
				(HighPrint 76 72)
				;"You have destroyed the plant that was to be preserved!"
				(HighPrint 76 73)
				;"As you have destroyed, so shall you now become!"
				(self cue:)
			)
			(1
				(dryad
					setLoop: 3
					cel: 0
					cycleSpeed: 2
					setCycle: CycleTo 2 1 self
				)
			)
			(2
				(ego
					view: vEgoKilledByDryad
					setLoop: 0
					cel: 0
					cycleSpeed: 2
					setCycle: EndLoop self
				)
				(dryad setCycle: EndLoop)
			)
			(3
				(ego setLoop: 2 cel: 0 cycleSpeed: 1 setCycle: EndLoop self)
			)
			(4
				(ego
					view: vEgoCatchSeed
					setLoop: 1
					cel: 0
					posn: (+ (ego x?) 6) (+ (ego y?) 5)
					forceUpd:
				)
				(= seconds 2)
			)
			(5
				(++ local5)
				(ego setLoop: 1 setCycle: EndLoop self)
			)
			(6
				(seed
					posn: (ego x?) (- (ego y?) 29)
					yStep: 10
					startUpd:
					show:
				)
				(self cue:)
			)
			(7
				(spitSound play:)
				(ego setCycle: BegLoop)
				(seed
					setMotion: MoveTo (seed x?) (- (seed y?) 30) self
				)
			)
			(8
				(ego setLoop: 2 setCycle: EndLoop)
				(seed
					yStep: 10
					setMotion: MoveTo (seed x?) (+ (seed y?) 17) self
				)
			)
			(9
				(gulpSound play:)
				(seed hide:)
				(ego setCycle: BegLoop self)
			)
			(10
				(if (< local5 4)
					(self changeState: 5)
				else
					(self cue:)
				)
			)
			(11
				(EgoDead 76 74
					#title {Plant it, fella!}
					#icon vEgoKilledByDryad 2 6)
					;Well, it looks like the Dryad really made you eat dirt.
					;You're the spitting image of a Spirea plant, and you'll be hanging around for a long, long time.
			)
		)
	)
)

(instance egoToStag of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(stag setScript: stagBolts)
				(cond 
					((Btst STAG_HURT)
						(HighPrint 76 75)
						;"You have attempted to harm a free creature of the forest!"
						(HighPrint 76 76)
						;"You shall now become a part of the forest!"
						)
					(castFlame
						(HighPrint 76 77)
						;"You have misused your skills, oh Magician!  You have used the Flame Dart on my forest."
						(HighPrint 76 78)
						;"You must be made less dangerous."
						)
					(else
						(HighPrint 76 79)
						;"You dare to harm the Dryad of this forest!  You must be made less dangerous."
						)
				)
				(self cue:)
			)
			(1
				(dryad
					setLoop: 3
					cel: 0
					cycleSpeed: 2
					setCycle: CycleTo 2 1 self
				)
			)
			(2
				(ego
					view: vEgoKilledByDryad
					setLoop: 0
					cel: 0
					cycleSpeed: 2
					setCycle: EndLoop self
				)
				(dryad setCycle: EndLoop)
			)
			(3
				(ego setLoop: 1 cel: 0 setCycle: EndLoop self)
			)
			(4
				(EgoDead 76 80
					#title {Oh, deer!}
					#icon vEgoKilledByDryad 1 5)
					;You are staggered by the Dryad's spell.  She appears to enjoy having lots of bucks.
			)
		)
	)
)

(instance goToSleep of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego illegalBits: 0 setMotion: MoveTo 201 132 self)
			)
			(1
				(= currentPalette 1)
				(curRoom drawPic: 76 6)
				(bush ignoreActors: init: stopUpd: addToPic:)
				(ego view: vEgoCatchSeed setLoop: 6 setCel: 0)
				(= seconds 3)
			)
			(2
				(TimePrint 5 76 81)
				;You sleep comfortably and well in this quiet and protected corner of the woods.
				(= seconds 5)
			)
			(3
				(HandsOn)
				(EgoSleeps 6 0)
				(= currentPalette 0)
				(curRoom drawPic: 76 7)
				(bush ignoreActors: init: stopUpd: addToPic:)
				(ego posn: 201 132 setLoop: 2)
				(NormalEgo)
				(client setScript: 0)
			)
		)
	)
)
