;;; Sierra Script 1.0 - (do not remove this comment)
(script# 313)
(include game.sh)
(use Main)
(use Intrface)
(use LoadMany)
(use Smooper)
(use Chase)
(use RFeature)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm313 0
)

(local
	birdcage
	drawer
	lamp
	basket
	purse
	rubberPlant
	coleusPlant
	leftCandle
	rightCandle
	climbUpState
	local10
	catNeedy
	local12
	catAttackCued
	wentUpstairs
	pettingCat
	feedCat
	cageUncovered
)
(procedure (LookAround)
	(HighPrint 313 0)
	;The smell of lavender and dust fills your nose as you walk in.  This reminds you of a great aunt's house you once visited.
	;The couch looks every bit as uncomfortable as your aunt's.
	(HighPrint 313 1)
	;There is a covered birdcage near the stairs, and a knitting basket beside the couch.
)

(procedure (PetCat)
	(SolvePuzzle f313PetCat 3 THIEF)
	(cond 
		((or (== (catWalk state?) 6) (== (catWalk state?) 7))
			(= local12 0)
			(catWalk changeState: 8)
		)
		((& (ego onControl: origin) cYELLOW)
			(HighPrint 313 2)
			;Just wait. He'll catch up to you
		)
		(else
			(NotClose)
		)
	)
)

(procedure (SearchPurse)
	(cond 
		((Btst fSearchedPurse)
			(AlreadyDone)
		)
		((< (ego distanceTo: purse) 15)
			(HighPrint 313 3)
			;In the purse, you find 20 silvers and some soiled hankies. You take the silver.
			(ego get: iSilver 20)
			(Bset fSearchedPurse)
			(SolvePuzzle f313SearchPurse 1 THIEF)
		)
		(else
			(NotClose)
		)
	)
)

(procedure (SearchBasket)
	(cond 
		((Btst fSearchedBasket)
			(AlreadyDone)
		)
		((< (ego distanceTo: basket) 15)
			(HighPrint 313 4)
			;A string of pearls seems to have fallen into the bag among the knitting. You take the pearls, of course.
			(ego get: iPearls)
			(Bset fSearchedBasket)
			(SolvePuzzle f313SearchBasket 1 THIEF)
		)
		(else
			(NotClose)
		)
	)
)

(procedure (SearchDesk)
	(cond 
		((Btst fSearchedDesk)
			(HighPrint 313 5)
			;You find nothing else of value in the desk.
			)
		((ego inRect: 22 131 53 152)
			(SolvePuzzle f313SearchDesk 1 THIEF)
			(ego setScript: deskOpen)
		)
		(else
			(NotClose)
		)
	)
)

(procedure (UncoverBirdcage)
	(cond 
		((Btst fUncoveredCage)
			(AlreadyDone)
		)
		((< (ego distanceTo: birdcage) 25)
			(ego setScript: birdieSings)
		)
		(else
			(NotClose)
		)
	)
)

(instance cat of Actor)

(instance catTurn of SmoothLooper
	(properties
		vChangeDir vCat
	)
)

(instance rm313 of Room
	(properties
		picture 313
		style WIPELEFT
	)
	
	(method (init)
		(LoadMany VIEW vLOLInside vMagicCat vEgoDeathByCat vEgoBigGrin)
		(Load SOUND 8)
		(Load SOUND (SoundFX 52))
		(super init:)
		(SolvePuzzle f313EnterLOLHouse 5 THIEF)
		(mouseDownHandler add: self)
		(StatusLine enable:)
		(NormalEgo)
		(ego posn: 159 189 init: setMotion: MoveTo 159 170)
		(onCouch init:)	;EO: added so it gets used
		(= deathMusic (SoundFX 52))
		(cat
			view: vMagicCat
			loop: 4
			posn: 280 165
			init:
			moveSpeed: 1
			setStep: 3 1
			setCycle: Walk
			setScript: catWalk
		)
		((= birdcage (Prop new:))
			view: vLOLInside
			loop: 2
			cel: 0
			posn: 209 110
			z: 22
			init:
			stopUpd:
		)
		((= drawer (Prop new:))
			view: vLOLInside
			loop: 1
			cel: 0
			posn: 31 135
			init:
			setPri: 10
			stopUpd:
		)
		((= lamp (View new:))
			view: vLOLInside
			loop: 2
			cel: 2
			posn: 279 95
			stopUpd:
			addToPic:
		)
		((= rubberPlant (View new:))
			view: vLOLInside
			loop: 2
			cel: 3
			posn: 71 116
			stopUpd:
			addToPic:
		)
		((= coleusPlant (View new:))
			view: vLOLInside
			loop: 2
			cel: 4
			posn: 28 124
			setPri: 11
			stopUpd:
			addToPic:
		)
		((= purse (View new:))
			view: vLOLInside
			loop: 2
			cel: 6
			posn: 81 141
			z: 12
			init:
			setPri: 10
			stopUpd:
		)
		((= basket (View new:))
			view: vLOLInside
			loop: 2
			cel: 5
			posn: 172 136
			setPri: 10
			init:
			stopUpd:
		)
		((View new:)
			view: vLOLInside
			loop: 0
			cel: 0
			posn: 117 149
			ignoreActors:
			setPri: 12
			init:
			stopUpd:
			addToPic:
		)
		(if (not (Btst fStoleCandles))
			((= leftCandle (View new:))
				view: vLOLInside
				loop: 2
				cel: 1
				posn: 87 148
				ignoreActors:
				setPri: 12
				init:
				stopUpd:
			)
			((= rightCandle (View new:))
				view: vLOLInside
				loop: 2
				cel: 1
				posn: 142 148
				ignoreActors:
				setPri: 12
				init:
				stopUpd:
			)
		)
		(self setLocales: TOWN)
		(self setScript: first313Script)
		(sillyThief init: play:)
	)
	
	(method (doit)
		(cond 
			((> (ego y?) 189)
				(= dayLOLBreakIn Day)
				(curRoom newRoom: 310)
			)
			(
				(and
					(& (ego onControl: origin) cLMAGENTA)
					(== (ego loop?) 1)
					(!= climbUpState 1)
				)
				(= climbUpState 1)
				(ego setScript: climbUp1)
			)
			(
				(and
					(& (ego onControl: origin) cLRED)
					(== (ego loop?) 1)
					(!= climbUpState 2)
				)
				(= climbUpState 2)
				(ego setScript: climbUp2)
			)
			(
				(and
					(& (ego onControl: origin) cLCYAN)
					(== (ego loop?) 1)
					(!= climbUpState 3)
				)
				(= climbUpState 3)
				(ego setScript: climbUp3)
			)
			((and (< (ego y?) 103) (== (ego loop?) 0))
				(ego setScript: climbDown)
			)
			((== (ego onControl: origin) cLGREEN)
				(= climbUpState 0)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(Bset fBeenIn313)
		(= deathMusic (SoundFX 26))
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'search,(look<in)>')
				(cond 
					((Said '/purse')
						(SearchPurse)
					)
					((Said '/bag,basket')
						(SearchBasket)
					)
					((Said '/birdcage,birdcage')
						(UncoverBirdcage)
					)
					((Said '/couch')
						(cond 
							((Btst fSearchedCouch)
								(HighPrint 313 6)
								;You find nothing else by searching the couch.
								)
							((ego inRect: 80 125 150 145)
								(HighPrint 313 7)
								;You find 3 silvers that have fallen down into the cushions.
								(ego get: iSilver 3)
								(Bset fSearchedCouch)
								(SolvePuzzle f313SearchCouch 1 THIEF)
							)
							(else
								(NotClose)
							)
						)
					)
					((Said '/desk,drawer')
						(SearchDesk)
					)
					((Said '/plant')
						(if
							(or
								(< (ego distanceTo: rubberPlant) 15)
								(ego inRect: 22 131 53 152)
							)
							(HighPrint 313 8)
							;Nothing but dirt and leaves there.
						else
							(NotClose)
						)
					)
					(else
						(event claimed: FALSE)
					)
				)
			)
			((Said 'open>')
				(cond 
					((Said '/desk,drawer')
						(SearchDesk)
					)
					((Said '/purse')
						(SearchPurse)
					)
					((Said '/bag,basket')
						(SearchBasket)
					)
					((Said '/birdcage,birdcage')
						(UncoverBirdcage)
					)
				)
			)
			((Said 'get>')
				(cond 
					((Said '/candlestick,stick,holder,candle')
						(cond 
							((Btst fStoleCandles)
								(AlreadyDone)
							)
							((ego inRect: 62 145 166 165)
								(HighPrint 313 9)
								;You take the silver candlesticks and stow them in your pack.
								(ego get: iCandlesticks 2)
								(leftCandle dispose:)
								(rightCandle dispose:)
								(Bset fStoleCandles)
								(SolvePuzzle f313StealCandles 1 THIEF)
							)
							(else
								(NotClose)
							)
						)
					)
					((Said '/purse')
						(SearchPurse)
					)
					((Said '/bag,basket')
						(SearchBasket)
					)
					((Said '/couch')
						(HighPrint 313 10)
						;Call Mayflower!
					)
				)
			)
			(
				(or
					(Said 'lift,get/cover')
					(Said 'uncover/birdcage,birdcage')
				)
				(UncoverBirdcage)
			)
			((Said 'look[<at,around][/!*,room,building]')
				(LookAround)
			)
			((or (Said 'look<up') (Said 'look/ceiling'))
				(HighPrint 313 11)
				;Nothing up there.
			)
			((or (Said 'look<down') (Said 'look/floor'))
				(HighPrint 313 12)
				;The rug is blue, and the floor has recently been painted red.
			)
			((Said 'look/dust')
				(HighPrint 313 13)
				;The house is tidy, but there is a layer of dust over everything.
			)
			(
				(or
					(MouseClaimed onRug event shiftDown)
					(Said 'look/carpet')
				)
				(HighPrint 313 14)
				;The oval rug is blue to match the walls.
			)
			((Said 'look/ladder,door')
				(HighPrint 313 15)
				;The stairs lead to the door to an upstairs room.
			)
			((MouseClaimed onDoor event shiftDown)
				(HighPrint 313 16)
				;The door to the upstairs room.
			)
			((Said 'look/wall')
				(HighPrint 313 17)
				;The walls are a deep shade of blue.
			)
			(
				(or
					(MouseClaimed onFireplace event shiftDown)
					(Said 'look/chimney')
				)
				(HighPrint 313 18)
				;The fire is out, but the heat from the hearth is still felt in the room.
			)
			( ;EO: What's with this duplicate entry?
				(or
					(MouseClaimed onFireplace event shiftDown)
					(Said 'look/chimney')
				)
				(HighPrint 313 18)
				;The fire is out, but the heat from the hearth is still felt in the room.
			)
			(
				(or
					(MouseClaimed onPurse event shiftDown)
					(Said 'look/purse,couch')
				)
				(HighPrint 313 19)
				;The Little Old Lady left her purse on the couch.  Mighty careless.
			)
			(
				(or
					(MouseClaimed onLamp event shiftDown)
					(Said 'look/mantle')
				)
				(HighPrint 313 20)
				;On the mantle sits an ordinary hurricane lamp which provides the only light in the room.
			)
			(
				(or
					(MouseClaimed onLCandle event shiftDown)
					(MouseClaimed onRCandle event shiftDown)
					(Said 'look/candle,candlestick')
				)
				(if (not (ego has: iCandlesticks))
					(HighPrint 313 21)
					;The candlesticks are heavy, ornate and made of sterling silver.
				)
			)
			((Said 'look/table')
				(if (ego has: iCandlesticks)
					(HighPrint 313 22)
					;There is a lace doily on the wooden table.
				else
					(HighPrint 313 23)
					;There is a lace doily in the center of the wooden table and a pair of silver candlesticks on either side of it.
				)
			)
			(
				(or
					(MouseClaimed onDoily event shiftDown)
					(Said 'look/doily')
				)
				(HighPrint 313 24)
				;The doily is lacy and shows fine workmanship. It is slightly yellow with age.
			)
			(
				(or
					(MouseClaimed onCage event shiftDown)
					(Said 'look/birdcage,birdcage,stand,cover')
				)
				(HighPrint 313 25)
				;The birdcage is on a tall brass stand. There is a cover draped over it to keep the bird warm and quiet.
			)
			((Said 'look/bird')
				(if cageUncovered
					(HighPrint 313 26)
					;Cute but NOISY!.
				else
					(HighPrint 313 27)
					;The cage is covered, and you can't see the bird.
				)
			)
			((or (MouseClaimed cat event shiftDown) (Said 'look/cat'))
				(HighPrint 313 28)
				;The little house cat seems harmless, but strangely restless.
			)
			(
				(or
					(MouseClaimed onDesk event shiftDown)
					(Said 'look/desk')
				)
				(HighPrint 313 29)
				;Against the wall, there is a small wooden desk with a potted plant on it. The desk has one drawer.
			)
			((MouseClaimed onRubberPlant event shiftDown)
				(HighPrint 313 30)
				;It's a rubber plant.
			)
			((MouseClaimed onColeusPlant event shiftDown)
				(HighPrint 313 31)
				;The Coleus plant has brightly colored leaves.
			)
			((Said 'look/plant')
				(HighPrint 313 32)
				;On the desk there is a plant which looks like some kind of violet. In the corner is a taller, leafy house plant.
			)
			((Said 'look/pan')
				(HighPrint 313 33)
				;There are no drugs here.
			)
			((Said 'look/railing')
				(HighPrint 313 34)
				;It's part of the stairs.
			)
			(
				(or
					(MouseClaimed onBag event shiftDown)
					(Said 'look/bag,basket')
				)
				(HighPrint 313 35)
				;The knitting bag is decorated on the outside with knitted figures. The Little Old Lady must do knitting when she's not asleep.
			)
			((Said 'look/collar')
				(HighPrint 313 36)
				;The little cat is wearing a cheap rhinestone collar.
			)
		)
	)
)

(instance birdieSings of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset fUncoveredCage)
				(= cageUncovered TRUE)
				(birdcage setLoop: 3 setCycle: Forward startUpd:)
				(= seconds 6)
			)
			(1
				(Print 313 37 #at -1 15 #title {Little Birdie})
				;CHIRP! CHIRP!
				(Print 313 38 #at -1 15 #title {Little Old Lady})
				; Kitty, you'd better not be bothering the little birdie again!				
				(HighPrint 313 39)
				;Who would think a little birdie could be so noisy!
				(self cue:)
			)
			(2
				(birdcage setLoop: 2 setCel: 0 stopUpd:)
				(= cycles 2)
			)
			(3
				(HighPrint 313 40)
				;You quickly cover the cage and hope the bird shuts up.
				(= cageUncovered FALSE)
				(ego setScript: 0)
			)
		)
	)
)

(instance deskOpen of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(drawer setCycle: EndLoop self)
			)
			(1
				(HighPrint 313 41)
				;You find 1 silver in the desk drawer. You find nothing else of any value to you.
				(ego get: iSilver 1)
				(Bset fSearchedDesk)
				(self cue:)
			)
			(2
				(drawer setCycle: BegLoop)
				(ego setScript: 0)
				(HandsOn)
			)
		)
	)
)

(instance first313Script of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds 2)
			)
			(1
				(if (or (not (Btst fBeenIn313)) (!= prevRoomNum 0))
					(LookAround)
				)
				(client setScript: 0)
			)
		)
	)
)

(instance catWalk of Script
	(method (doit)
		(cond 
			(catAttackCued
				(= catAttackCued FALSE)
				(cat setScript: getReady)
			)
			(
				(and
					(& (ego onControl: origin) cYELLOW)
					(> (ego distanceTo: cat) 17)
					(not local10)
				)
				(self changeState: 5)
			)
			(
				(and
					(>= state 5)
					(== climbUpState 0)
					(not (& (ego onControl: origin) cYELLOW))
				)
				(= local10 0)
				(self changeState: 0)
			)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cat setLoop: 4 setMotion: 0 cycleSpeed: 4)
				(if (and (not local10) (not wentUpstairs))
					(= seconds 4)
				)
			)
			(1
				(if (not (cat looper?))
					(cat setLoop: catTurn)
				)
				(cat
					setLoop: -1
					loop: 0
					cel: 0
					cycleSpeed: 1
					moveSpeed: 1
					xStep: 3
					yStep: 1
					setMotion: MoveTo 240 123 self
				)
			)
			(2
				(cat setMotion: MoveTo 180 123 self)
			)
			(3
				(cat setMotion: MoveTo 220 165 self)
			)
			(4
				(= state 0)
				(cat setMotion: MoveTo 280 165 self)
			)
			(5
				(= local10 1)
				(= seconds 0)
				(if (not (cat looper?)) (cat setLoop: catTurn))
				(cat
					setLoop: -1
					loop: 0
					cel: 0
					cycleSpeed: 1
					setMotion: Chase ego 15 self
				)
			)
			(6
				(HandsOff)
				(= local10 0)
				(++ local12)
				(cat loop: 4 setMotion: 0 cycleSpeed: 4)
				(if (== local12 2) (= cycles 2)
				else
					(HandsOn)
				)
			)
			(7
				(= local12 0)
				(++ catNeedy)
				(HandsOn)
				(switch catNeedy
					(1
						(HighPrint 313 43)
						;The cat seems to want something.
					)
					(2
						(HighPrint 313 44)
						;The cat seems more insistent than before.
					)
					(3
						(HandsOff)
						(TimePrint 6 313 42)
						;You have a bad feeling about the very deep, low growl emanating from the cat.
						(cat setScript: gonnaGetYou)
					)
				)
			)
			(8
				(HandsOff)
				(Face ego cat)
				(= cycles 2)
			)
			(9
				(if pettingCat
					(switch catNeedy
						(0
							(HighPrint 313 45)
							;You pet the nice kitty.
						)
						(1
							(HighPrint 313 46)
							;The cat really likes being petted.
						)
						(2
							(HighPrint 313 47)
							;This is a very insistent cat.
						)
					)
				)
				(if feedCat
					(switch catNeedy
						(0
							(HighPrint 313 48)
							;You feed the nice kitty a crumb.
						)
						(1
							(HighPrint 313 49)
							;The cat really likes being fed. You give it a leftover morsel.
						)
						(2
							(if (ego has: iRations)
								(ego use: iRations 1)
								(HighPrint 313 50)
								;This is a very insistent cat. You give it one of your rations.
							else
								(HighPrint 313 51)
								;You have nothing substantial to feed the cat.
							)
						)
					)
				)
				(= feedCat FALSE)
				(= pettingCat FALSE)
				(if (< catNeedy 3)
					(HighPrint 313 52)
					;Purrrrrrrr!
				)
				(if (> catNeedy 0)
					(if feedCat
						(= catNeedy 0)
					else
						(-- catNeedy)
					)
				)
				(HandsOn)
				(= state 7)
			)
		)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(saidEvent
				(cond 
					((Said 'pat[/cat]')
						(= pettingCat TRUE)
						(PetCat)
					)
					((Said 'feed/cat')
						(= feedCat TRUE)
						(PetCat)
					)
					((Said 'beat,kill/cat')
						(if (or (== (catWalk state?) 6) (== (catWalk state?) 7))
							(HandsOff)
							(TimePrint 6 313 42)
							;You have a bad feeling about the very deep, low growl emanating from the cat.
							(cat setScript: gonnaGetYou)
						else
							(NotClose)
						)
					)
				)
			)
		)
		(super handleEvent: event)
	)
)

(instance gonnaGetYou of Script
	(method (doit)
		(cond 
			(
				(and
					(== (ego x?) 194)
					(== (ego y?) 144)
					(== (cat x?) 260)
					(== (cat y?) 142)
				)
				(cat illegalBits: cWHITE setScript: 0)
				(ego setScript: catAttack)
			)
			((and (== (ego x?) 194) (== (ego y?) 144))
				(if (not (if (== (cat x?) 260) (== (cat y?) 142)))
					(ego view: vEgoBigGrin loop: 0 cel: 0)
				)
			)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not (cat looper?)) (cat setLoop: catTurn))
				(cat
					setLoop: -1
					loop: 0
					setStep: 3 1
					cycleSpeed: 1
					illegalBits: 0
					setMotion: MoveTo 260 142
				)
				(ego
					illegalBits: 0
					ignoreActors:
					setMotion: MoveTo 194 144
				)
			)
		)
	)
)

(instance getReady of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not (cat looper?)) (cat setLoop: catTurn))
				(cat
					setLoop: -1
					loop: 0
					cycleSpeed: 1
					setMotion: MoveTo 260 142 self
				)
			)
			(1
				(cat setMotion: 0 loop: 4 cycleSpeed: 4)
			)
		)
	)
)

(instance catAttack of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if wentUpstairs
					(ego setMotion: MoveTo 194 144 self)
				else
					(self cue:)
				)
			)
			(1
				(if wentUpstairs
					(ego view: vEgoBigGrin setLoop: 0 cel: 0)
				)
				(cat
					illegalBits: 0
					ignoreActors:
					looper: 0
					setLoop: 5
					cel: 0
					cycleSpeed: 2
					setCycle: EndLoop self
				)
			)
			(2
				(ego cycleSpeed: 1 setCycle: EndLoop self)
			)
			(3
				(ego setLoop: 1 cel: 0)
				(= cycles 4)
			)
			(4
				(ego
					cycleSpeed: 0
					setCycle: Forward
					setMotion: MoveTo 160 144 self
				)
				(cat loop: 6 cel: 0 cycleSpeed: 1 setCycle: Forward)
			)
			(5
				(ego setCycle: 0)
				(cat loop: 7 cel: 0 setCycle: EndLoop self)
			)
			(6
				(cat posn: 243 141)
				(= cycles 1)
			)
			(7
				(cat posn: 226 140)
				(ego view: vEgoDeathByCat setLoop: 0 setCel: 0)
				(= cycles 1)
			)
			(8
				(cat posn: 209 139)
				(ego setCel: 1)
				(= cycles 1)
			)
			(9
				(cat hide:)
				(ego setCel: 2)
				(= cycles 1)
			)
			(10
				(ego setLoop: 1 setCel: 0)
				(cat
					view: vEgoDeathByCat
					loop: 2
					cel: 0
					setPri: 12
					posn: 134 126
					setCycle: Forward
					show:
				)
				(= cycles 40)
			)
			(11
				(if wentUpstairs
					(EgoDead 313 53
						#title { Better stay downstairs next time._}
						#icon vCat 1 0)
						;The Little Old Lady's cries finally bring the Sheriff and Otto to her rescue.
						;They find you pinned to the floor, unconscious, dreaming deliriously of death by sandpaper.
				else
					(EgoDead 313 54
						#title { What a cute little kitty!_}
						#icon vCat 1 0)
						;When the Little Old Lady awakes to see what's going on, you have to concede to her (through lips that are as raw as hamburger)
						;that you've been licked!  She summons the Sheriff and his goon, Otto.
				)
			)
		)
	)
)

(instance climbUp1 of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= catAttackCued TRUE)
				(ego
					setLoop: 1
					illegalBits: 0
					setMotion: MoveTo 177 101 self
				)
			)
			(1
				(HandsOn)
				(ego setLoop: -1 illegalBits: cWHITE)
				(Print 313 55 #at 78 101)
				(ego setScript: 0)
				; SQUEEEEAK!
			)
		)
	)
)

(instance climbUp2 of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					illegalBits: 0
					setLoop: 1
					setMotion: MoveTo 160 90 self
				)
			)
			(1
				(HandsOn)
				(ego setLoop: -1 illegalBits: cWHITE)
				(Print 313 56 #at 62 92)
				(ego setScript: 0)
				; CREEEEAAK!
			)
		)
	)
)

(instance climbUp3 of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					illegalBits: 0
					setLoop: 1
					setMotion: MoveTo 146 74 self
				)
			)
			(1
				(HandsOn)
				(ego setLoop: -1 illegalBits: cWHITE)
				(Print 313 57 #at 41 88)
				;SCRAAAAWK!
				(HighPrint 313 58)
				;The owner of the house awakens...
				(Print 313 59 #at -1 20 #mode teJustCenter #title {Little Old Lady})
				(= wentUpstairs TRUE)
				(ego setScript: climbDown)
				; Help! Burglars! Sheriff! Help!
; Kitty! Kitty!
			)
		)
	)
)

(instance climbDown of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= climbUpState 0)
				(ego
					illegalBits: 0
					setLoop: 0
					setMotion: MoveTo 194 107 self
				)
			)
			(1
				(if wentUpstairs
					(Print 313 60 #at -1 15 #title {L.O.L.})
					(ego setLoop: -1 setScript: catAttack)
					(cat setScript: 0)
					; KITTY!!!
				else
					(HandsOn)
					(ego setLoop: -1 illegalBits: cWHITE)
					(ego setScript: 0)
					(cat setScript: catWalk)
				)
			)
		)
	)
)

(instance onPurse of RFeature
	(properties
		nsTop 119
		nsLeft 75
		nsBottom 129
		nsRight 85
	)
)

(instance onBag of RFeature
	(properties
		nsTop 122
		nsLeft 165
		nsBottom 135
		nsRight 177
	)
)

(instance onCage of RFeature
	(properties
		nsTop 69
		nsLeft 202
		nsBottom 88
		nsRight 214
	)
)

(instance onCouch of RFeature
	(properties
		nsTop 112
		nsLeft 86
		nsBottom 131
		nsRight 152
	)
)

(instance onRubberPlant of RFeature
	(properties
		nsTop 77
		nsLeft 63
		nsBottom 115
		nsRight 76
	)
)

(instance onColeusPlant of RFeature
	(properties
		nsTop 107
		nsLeft 19
		nsBottom 123
		nsRight 33
	)
)

(instance onDoor of RFeature
	(properties
		nsTop 6
		nsLeft 70
		nsBottom 50
		nsRight 105
	)
)

(instance onFireplace of RFeature
	(properties
		nsTop 93
		nsLeft 259
		nsBottom 143
		nsRight 288
	)
)

(instance onLamp of RFeature
	(properties
		nsTop 73
		nsLeft 274
		nsBottom 94
		nsRight 284
	)
)

(instance onDesk of RFeature
	(properties
		nsTop 124
		nsLeft 19
		nsBottom 137
		nsRight 43
	)
)

(instance onDoily of RFeature
	(properties
		nsTop 144
		nsLeft 97
		nsBottom 150
		nsRight 130
	)
)

(instance onLCandle of RFeature
	(properties
		nsTop 133
		nsLeft 83
		nsBottom 149
		nsRight 91
	)
)

(instance onRCandle of RFeature
	(properties
		nsTop 133
		nsLeft 137
		nsBottom 149
		nsRight 146
	)
)

(instance onRug of RFeature
	(properties
		nsTop 136
		nsLeft 163
		nsBottom 158
		nsRight 223
	)
)

(instance sillyThief of Sound
	(properties
		number 8
		loop -1
	)
)
