;;; Sierra Script 1.0 - (do not remove this comment)
(script# rMeepsPeep)
(include game.sh)
(use Main)
(use CastDart)
(use ThrowKnife)
(use ThrowRock)
(use CastDazz)
(use Intrface)
(use LoadMany)
(use Sound)
(use Save)
(use Motion)
(use Game)
(use Invent)
(use Actor)
(use System)

(public
	rm60 0
)
(enum
	askFRUIT
	askBABY
	askFUR
	askSCROLL
)

(local
	maxMeeps
	meepsOnScreen
	[meep 8]
	[meepScript 8]
	[eye 8]
	newAct
	local80_2
	bossOnScreen
	local100_2
	talkRet
	local56_3
	newAct_2 =  -1
	metBoss
	local96_3
	meepsScared
	roomSysTime
	local56_3_2
	newAct_3_2
	local80_3_2
	local96_3_2
	local100_4
	local300_3_2
	local56_2
	babyOnScreen
	scrollOnGround
	furOnGround
	local100_2_2 =  25
	meepPri = [11 8 10 6 9 7 11 6]
	meepPosn = [41 167 65 135 104 157 130 114 180 146 212 125 243 167 269 114]
	theNewAct_2 = [3 0 2 4 6 7 5 1]
	local80_3_2_2 = [160 105 71 158 134 148 209 137 273 159 299 105 242 117 95 127]
	askedFor = [{some fruit} {a baby meep} {some green fur} {a magic spell scroll}]
	[str 200]
	local300_3_2_2
	local301
)
(procedure (BossSays seconds)
	(Print
		&rest
		#at -1 2
		#width 0
		#mode teJustCenter
		#window meepWin
		#dispose
		#time seconds
	)
)

(procedure (AddMeeps &tmp i)
	(for ((= i 0)) (< i 8) ((++ i))
		(= [meep i] (Clone aMeep))
		(= [eye i] (Clone anEye))
		(= [meepScript i] (Clone aMeepScript))
		([meep i]
			setLoop: 5
			cel: 0
			setPri: (+ 1 [meepPri i])
			posn:
				[meepPosn (* i 2)]
				(- [meepPosn (+ (* i 2) 1)] 4)
			init:
			stopUpd:
			setScript: [meepScript i] 0 i
		)
		([eye i]
			setLoop: 3
			cel: 0
			setPri: (+ 1 [meepPri i])
			z: 1
			posn:
				[meepPosn (* i 2)]
				(- [meepPosn (+ (* i 2) 1)] 17)
			init:
			stopUpd:
			hide:
		)
	)
)

(instance bossBlock of Block
	(properties
		top 104
		left 117
		bottom 120
		right 144
	)
)

(instance aMeep of Actor
	(properties
		view rMeepsPeep
		loop 5
		illegalBits $0000
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(and
					(== (event type?) mouseDown)
					(MouseClaimed self event shiftDown)
				)
				(cond 
					((and (== loop 5) (== cel 0))
						(HighPrint 60 0)
						;This large rock covers a Meep hole.
					)
					((== loop 0)
						(HighPrint 60 1)
						;A furry blue Meep
					)
					((and (== loop 1) (== view rMeepsPeep))
						(HighPrint 60 2)
						;A furry purple Meep
					)
					(else
						(HighPrint 60 3)
						;A furry green Meep
					)
				)
			)
		)
	)
)

(instance anEye of Prop
	(properties
		view rMeepsPeep
		loop 3
		cycleSpeed 1
	)
)

(instance meepHead of Prop
	(properties
		view vGreenMeep
		loop 3
	)
)

(instance bossRock of Actor
	(properties
		yStep 5
		view rMeepsPeep
		loop 5
		illegalBits $0000
		xStep 5
	)
)

(instance babyMeep of Actor
	(properties
		z 2
		view vGreenMeep
		loop 7
		illegalBits $0000
	)
)

(instance greenFur of Actor
	(properties
		z 2
		view vGreenMeep
		loop 8
		illegalBits $0000
	)
)

(instance spellScroll of Actor
	(properties
		z 2
		view vGreenMeep
		loop 5
		illegalBits $0000
	)
)

(instance converse of Sound
	(properties
		number 75
		priority 1
	)
)

(instance meepSound of Sound
	(properties
		number 55
		priority 1
	)
)

(instance babySound of Sound
	(properties
		number 74
		priority 2
	)
)

(instance thudSound of Sound
	(properties
		number 58
		priority 3
	)
)

(instance throwSound of Sound
	(properties
		number 31
		priority 3
	)
)

(instance meepWin of SysWindow
	(properties
		color vGREEN
	)
)

(instance rm60 of Room
	(properties
		picture 60
		style HSHUTTER
		east 61
	)
	(method (init)
		(LoadMany VIEW rMeepsPeep vGreenMeep vEgoSwordSpirea vEgoThrowing vEgoPickupMeep)
		(LoadMany SOUND
			(SoundFX 55)
			(SoundFX 56)
			(SoundFX 57)
			(SoundFX 74)
			(SoundFX 58)
			(SoundFX 75)
			54
			(SoundFX 31)
		)
		(if (ego knows: FLAMEDART)
			(Load VIEW vEgoMagicFlameDart)
			(Load SCRIPT 100)
		)
		(super init:)
		(cSound fade:)
		(StatusLine enable:)
		(NormalEgo)
		(ego
			posn: 318 176
			init:
			illegalBits: (| cWHITE cLGREY)
			observeBlocks: bossBlock
			setMotion: MoveTo 306 176
		)
		(= maxMeeps
			(switch howFast
				(slow 2)
				(medium 3)
				(else  4)
			))
		(meepSound number: (SoundFX 55) init:)
		(babySound number: (SoundFX 74) init:)
		(thudSound number: (SoundFX 58) init:)
		(throwSound number: (SoundFX 31) init:)
		(AddMeeps)
		(if (< numColors 8)
			(meepWin color: vBLACK back: vWHITE)
		)
		(directionHandler addToFront: self)
		(mouseDownHandler addToFront: self)
	)
	
	(method (doit &tmp thisTime)
		(if (!= roomSysTime (= thisTime (GetTime SYSTIME1)))
			(= roomSysTime thisTime)
			(if (and bossOnScreen (not local100_2) (not (-- local56_2)))
				([meep 3] setScript: bossMad 0 0)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(Bset fBeenIn60)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp temp0 spell)
		(= temp0 local80_2)
		(= local80_2 1)
		(switch (event type?)
			(saidEvent
				(cond 
					(
						(or
							(Said 'chop,beat,fight,kill,damage[/meep,creature,boulder]')
							(Said 'use,draw/blade')
						)
						(cond 
							(meepsScared
								(HighPrint 60 4)
								;No way!  Those Meeps are too fast and the rocks too hard for you to want to try that again!
							)
							(
								(or
									(< (ego y?) 102)
									(and (< (ego y?) 119) (< (ego x?) 110))
								)
								(HighPrint 60 5)
								;Try going over to where the Meeps are if you really want to hack them into little furry bits.
							)
							(else
								(= meepsScared TRUE)
								(= local80_2 0)
								(= local300_3_2_2 1)
								(HandsOff)
								(ego setScript: killMeeps)
								(if bossOnScreen
									([meep 3] setScript: bossMad killMeeps)
								else
									(killMeeps cue:)
								)
							)
						)
					)
					((Said 'cast>')
						(switch (= spell (SaidSpell event))
							(DETMAGIC
								(if (CastSpell spell)
									(HighPrint 60 6)
									;You detect no other magic here.
								)
							)
							(DAZZLE
								(if (CastSpell spell)
									(= meepsScared TRUE)
									(CastDazz)
									(if bossOnScreen
										([meep 3] setScript: bossMad)
									)
								)
							)
							(FLAMEDART
								(if (CastSpell spell)
									(= meepsScared TRUE)
									(if bossOnScreen
										([meep 3] setScript: bossMad)
									)
									(CastDart 0)
								)
							)
							(OPEN
								(if (CastSpell spell)
									(HighPrint 60 7)
									;The Open spell only works on locks and small closed things.
								)
							)
							(else
								(event claimed: FALSE)
							)
						)
					)
					((Said 'throw/dagger,dagger')
						(if (ThrowKnife 0)
							(= meepsScared TRUE)
							(if bossOnScreen
								([meep 3] setScript: bossMad)
							)
						)
					)
					((Said 'throw/boulder')
						(if (ego has: iRock)
							(= meepsScared TRUE)
							(if bossOnScreen
								([meep 3] setScript: bossMad)
							)
							(ThrowRock 0)
						else
							(HighPrint 60 8)
							;You don't have a rock
						)
					)
					((Said 'lift,(lockpick<up),get,capture/boulder,meep')
						(ego setScript: liftRock)
						)
					((Said 'get>')
						(cond 
							((Said '/fur[<green]')
								(if furOnGround
									(if (== (ego onControl: origin) cYELLOW)
										(ego setScript: getThing 0 2)
									else
										(HighPrint 60 9)
										;You can't get that from where you're standing.
									)
								else
									(HighPrint 60 10)
									;The Meeps would not appreciate it if you tried to take their fur -- the weather here is still very cold.
								)
							)
							((Said '/scroll,spell[<magic,spell,about]')
								(if scrollOnGround
									(if (== (ego onControl: origin) cYELLOW)
										(ego setScript: getThing 0 3)
									else
										(HighPrint 60 9)
										;You don't see a Spell Scroll here.
									)
								else
									(HighPrint 60 11)
									;You don't see a Spell Scroll here.
								)
							)
							((Said '/baby,(meep<baby)')
								(if babyOnScreen
									(if (== (bouncyBaby state?) 0)
										(bouncyBaby seconds: 0 cue:)
									)
									(TimePrint 4 60 12)
									;The baby Meep is just too quick for you.  Besides, they eat too much.
								else
									(HighPrint 60 13)
									;You don't see any baby Meeps at the moment.
								)
							)
							((Said '/apple,apple,core')
								(HighPrint 60 14)
								;You look at the apple cores, and quickly decide they are of no use to you.  Besides, they look disgusting.
							)
						)
					)
					((Said 'look>')
						(= local80_2 temp0)
						(cond 
							((Said '[<at,around][/!*,area,area,clearing]')
								(HighPrint 60 15)
								;You are in the Meeps' Peep.  The colorful, furry Meeps timidly pop out of their holes from time to time.
							)
							((Said '/baby,(meep<baby)')
								(HighPrint 60 16)
								;The baby Meep sure is cute.
							)
							((Said '/(meep,creature,animal)<green')
								(if meepsScared
									(HighPrint 60 17)
									;All of the Meeps are hiding deep within their holes.  Your vicious attack has them terrified.
									else
									(HighPrint 60 18)
									;You realize that you have never seen more than one green Meep at a time.  Perhaps there is only one.
								)
							)
							((Said '/(meep,creature,animal)<(magenta,blue)')
								(if meepsScared
									(HighPrint 60 17)
									;All of the Meeps are hiding deep within their holes.
									;Your vicious attack has them terrified.
								else
									(HighPrint 60 19)
									;There seems to be an abundant supply of blue and purple Meeps.
									;If only they'd stay still for a moment, perhaps you could count them.
								)
							)
							((Said '/meep,creature,animal')
								(if meepsScared
									(HighPrint 60 17)
									;All of the Meeps are hiding deep within their holes.
									;Your vicious attack has them terrified.
								else
									(HighPrint 60 20)
									;You see blue Meeps, purple Meeps, and occasionally a green Meep.
									;They seem to be very shy; whenever you approach one, it hides under its rock.
								)
							)
							((Said '/boulder,boulder')
								(HighPrint 60 21)
								;Large, heavy-looking rocks cover the many holes.
								;The Meeps must be stronger than they look to lift these rocks so easily.
							)
							((Said '/chasm')
								(if meepsScared
									(HighPrint 60 17)
									;All of the Meeps are hiding deep within their holes.
									;Your vicious attack has them terrified.
									else (HighPrint 60 22)
									;You keep seeing differently-colored Meeps come out of each hole.
									;They either lead to large underground caverns, or they are all connected under the earth.
								)
							)
							((Said '/bush,tree')
								(HighPrint 60 23)
								;The trees and bushes look much like those you've seen elsewhere in the valley.
							)
							((Said '/hill,hill')
								(HighPrint 60 24)
								;The mountains are very steep here.
							)
							((Said '/ground,down,grass')
								(HighPrint 60 25)
								;The meadow is covered with a light carpet of grass, broken up by the Meep holes.
							)
							((Said '/fur')
								(if furOnGround
									(HighPrint 60 26)
									;There is a wad of bright green fur on the ground.
								else
									(HighPrint 60 27)
									;The Meeps have brightly-colored, soft-looking fur.
									(if (ego has: iGreenFur)
										(event claimed: FALSE)
									)
								)
							)
							((Said '/scroll,spell')
								(cond 
									(scrollOnGround
										(HighPrint 60 28)
										;It looks like a Spell Scroll.
									)
									((Btst fLearnedDetect)
										(HighPrint 60 29)
										;The scroll is blank now, but once contained the Detect Magic spell.
									)
									(else
										(event claimed: FALSE)
									)
								)
							)
						)
					)
					((Said 'chat>')
						(= local56_2 30)
						(cond 
							(meepsScared
								(event claimed: TRUE)
								(HighPrint 60 30)
								;You can't talk to any of the Meeps.
								;They are all hiding deep within their holes, terrified after your vicious attack.
							)
							(bossOnScreen
								(event claimed: TRUE)
								(BossSays 5 60 31)
								;"Hey, just ask me about anything you want to know."
							)
							((== meepsOnScreen 0) (event claimed: TRUE)
								(HighPrint 60 32)
								;You don't see any Meeps to talk to at the moment.
							)
							(metBoss
								(event claimed: TRUE)
								([meep 3] setScript: bossRises)
							)
							(else
								(event claimed: TRUE)
								(self setScript: prepAsk)
							)
						)
					)
					((Said 'ask>')
						(= talkRet TRUE)
						(= local56_2 30)
						(cond 
							((and newAct (not bossOnScreen) (Said '//meep<green'))
								(= talkRet FALSE)
								([meep 3] setScript: bossRises)
							)
							((not bossOnScreen)
								(= talkRet FALSE)
								(event claimed: TRUE)
								(cond 
									(metBoss
										([meep 3] setScript: bossRises)
									)
									((== meepsOnScreen 0) (event claimed: TRUE)
										(HighPrint 60 32)
										;You don't see any Meeps to talk to at the moment.
									)
									(else
										(self setScript: prepAsk)
									)
								)
							)
							((Said '//boulder')
								(BossSays 5 60 33)
								;"We use rocks for doors.  They keep us dry and warm."
							)
							((Said '//meep,creature,animal')
								(BossSays 5 60 34)
								;"We are happy Meeps, living in our happy holes.  Don't worry.  Be happy!"
							)
							((Said '//apple,apple,stuff,junk')
								([meep 3] setScript: throwThings 0 0)
							)
							((Said '//fur<green')
								(if furOnGround
									(BossSays 5 60 35)
									;"Hey, go ahead, take it. It's yours."
								else
									([meep 3] setScript: throwThings 0 2)
								)
							)
							((Said '//fur')
								(BossSays 7 60 36)
								;"Hey, like fur is good stuff, keeps us warm.  Mine's the best -- it's like green, you know?"
							)
							((Said '//scroll,spell,magic,detect')
								(if scrollOnGround
									(BossSays 5 60 35)
									;"Hey, go ahead, take it. It's yours."
								else
									([meep 3] setScript: throwThings 0 3)
								)
							)
							(else
								(= talkRet FALSE)
								(event claimed: TRUE)
								(BossSays 5 60 37)
								;"Gee, boss, I really don't know much about that at all.  Hey, sorry."
							)
						)
						(if talkRet
							(SolvePuzzle f60TalkToMeep 1)
						)
					)
				)
			)
			(keyDown
				(= local80_2 temp0)
			)
			(mouseDown
				(if (& (event modifiers?) shiftDown)
					(= local80_2 temp0)
					(if (MouseClaimed ego event shiftDown)
						(HighPrint 60 38)
						;Why, that's me!
					)
				)
			)
		)
		(super handleEvent: event)
	)
)

(instance aMeepScript of Script
	(method (doit)
		(if
			(and
				(== state 2)
				local80_2
				(<= (ego distanceTo: client) 80)
			)
			(= cycles 1)
		)
		(super doit:)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0 (= cycles (Random 20 150)))
			(1
				(cond 
					((and bossOnScreen (== howFast slow))
						(self changeState: 0)
					)
					((>= meepsOnScreen maxMeeps)
						(self changeState: 0)
					)
					(meepsScared
						(self changeState: 0)
					)
					((and local80_2 (<= (ego distanceTo: client) 80))
						(self changeState: 0))
					(else
						(++ meepsOnScreen)
						(if (== register 3)
							(= newAct 1)
							(= temp0 2)
						else
							(= temp0 (Random 0 1))
						)
						(client
							posn: (client x?) (+ (client y?) 4)
							setPri: [meepPri register]
							setLoop: temp0
							setCel: -1
							cel: 0
							setCycle: EndLoop
						)
						(= cycles 2)
					)
				)
			)
			(2
				([eye register]
					setLoop: (+ 3 (Random 0 1))
					show:
					setCycle: Forward
				)
				(if (!= register newAct_2) (= cycles (Random 5 20)))
				(meepSound
					number: (SoundFX (+ (Random 0 2) 55))
					play:
				)
			)
			(3
				([eye register] setCycle: 0 stopUpd: hide:)
				(client setCycle: BegLoop self)
			)
			(4
				(meepSound stop:)
				(client
					posn: (client x?) (- (client y?) 4)
					setPri: (+ 1 [meepPri register])
					setLoop: 5
					setCel: 0
					stopUpd:
				)
				(if (== register 3) (= newAct 0))
				(-- meepsOnScreen)
				(self changeState: 0)
			)
		)
	)
)

(instance killMeeps of Script
	(method (init)
		(super init: &rest)
		(self setScript: hackMeep)
		(ChangeGait MOVE_RUN FALSE)
		(= local56_3 0)
		(= local96_3
			(switch howFast
				(slow 4)
				(medium 8)
				(fast 12)
				(else  16)
			)
		)
		(HandsOff)
		(ego ignoreBlocks: bossBlock)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1 (= cycles 2))
			(2
				(HandsOff)
				(= meepsScared 0)
				(++ maxMeeps)
				(= newAct_2 [theNewAct_2 (/ local56_3 2)])
				([meepScript newAct_2] changeState: 1)
				(-- maxMeeps)
				(= meepsScared 1)
				(= cycles 2)
			)
			(3
				(ego
					ignoreActors: TRUE
					illegalBits: 0
					setMotion: MoveTo
						[local80_3_2_2 local56_3]
						[local80_3_2_2 (+ local56_3 1)]
						self
				)
			)
			(4 (script cue:))
			(5
				(if (>= (+= local56_3 2) local96_3)
					(self cue:)
				else
					(self changeState: 2)
				)
			)
			(6
				(= newAct_2 -1)
				(ChangeGait MOVE_WALK FALSE)
				(ego ignoreActors: 0 illegalBits: (| cWHITE cLGREY) loop: 2)
				(= seconds 2)
			)
			(7
				(ego observeBlocks: bossBlock)
				(= local300_3_2_2 0)
				(HandsOn)
				(HighPrint 60 39)
				;Man, those rocks are tough!
				(client setScript: 0)
			)
		)
	)
)

(instance hackMeep of Script
	(method (changeState newState)
		(switch (= state newState)
			(1
				(ego view: vEgoSwordSpirea setLoop: 2 cel: 0 setCycle: EndLoop self)
			)
			(2
				(ego setLoop: 3 cel: 0 setCycle: EndLoop self)
				([meepScript newAct_2] changeState: 2 seconds: 0 cycles: 3)
			)
			(3 (ego setCycle: BegLoop self))
			(4
				(NormalEgo)
				(= state 0)
				(client cue:)
			)
		)
	)
)

(instance prepAsk of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= meepsScared TRUE)
				(= seconds 2)
			)
			(1
				(converse
					number: (SoundFX 75)
					loop: -1
					priority: 10
					play:
				)
				(TimePrint 7 60 40)
				;You hear squeaky muttering from beneath the ground.  It seems the Meeps are having quite a discussion about you.
				(= seconds 7)
			)
			(2
				(converse stop: loop: 1 priority: 1)
				([meep 3] setScript: bossRises)
				(client setScript: 0)
			)
		)
	)
)

(instance bossRises of Script
	(method (init)
		(= bossOnScreen TRUE)
		(super init: &rest)
	)
	
	(method (changeState newState &tmp clientX clientY clientPriority)
		(= clientX (client x?))
		(= clientY (client y?))
		(= clientPriority (client priority?))
		(switch (= state newState)
			(0
				(HandsOff)
				(= local56_2 30)
				(= meepsScared TRUE)
				(if (ego inRect: 115 104 130 145)
					(ego
						ignoreBlocks: bossBlock
						illegalBits: 0
						ignoreActors:
						setMotion: MoveTo 135 125 self
					)
				else
					(= cycles 4)
				)
			)
			(1
				(ego
					observeBlocks: bossBlock
					illegalBits: (| cWHITE cLGREY)
					ignoreActors: 0
				)
				([eye 3] setCycle: 0 stopUpd: hide:)
				(client
					ignoreActors:
					view: vGreenMeep
					setLoop: 2
					cel: 0
					setPri: -1
					setCycle: EndLoop
					setMotion: MoveTo 130 119 self
				)
				(if (not local80_3_2)
					(bossRock init:)
					(= local80_3_2 1)
				)
				(thudSound number: 54 play:)
				(bossRock
					ignoreActors:
					setLoop: 5
					cel: 0
					posn: 130 94
					setCycle: Forward
					setPri: 8
					setMotion: MoveTo 140 54 self
				)
			)
			(3
				(client
					setLoop: 1
					cel: 0
					setPri: 8
					cycleSpeed: 2
					setCycle: Forward
				)
				(bossRock setMotion: MoveTo 145 114 self)
				(if (or metBoss (Btst fMetMeepBoss))
					(BossSays 4 60 41)
					;Hiya, hiya.  Nice to seeya 'gain.
					;
				else
					(BossSays 4 60 42)
					;Hiya, hiya.  Pleased to meetcha.
					(Bset fMetMeepBoss)
					(= metBoss TRUE)
				)
			)
			(4
				(thudSound number: (SoundFX 58) play:)
				(bossRock
					ignoreActors: 0
					setCycle: 0
					setCel: 0
					setPri: -1
					stopUpd:
				)
				(= cycles 6)
			)
			(5
				(client ignoreActors: 0 setCycle: 0 cel: 3 stopUpd:)
				(if (not local96_3_2)
					(meepHead init:)
					(= local96_3_2 1)
				)
				(meepHead posn: 130 110 z: 1 setPri: 8 setCycle: Forward)
				(= seconds 2)
			)
			(6
				(meepHead setCycle: 0 stopUpd:)
				(= local56_2 30)
				(= meepsScared FALSE)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance bossHides of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if local96_3_2 (= local96_3_2 0) (meepHead dispose:))
				([meep 3]
					ignoreActors: 1
					setLoop: 2
					cel: 0
					setCycle: CycleTo 2 1
					setMotion: MoveTo 130 114 self
				)
			)
			(1
				([meep 3] setPri: [meepPri 3])
				(= seconds 3)
			)
			(2
				([meep 3]
					ignoreActors: 0
					setLoop: 0
					cel: 0
					posn: 130 127
				)
				(client setScript: 0)
			)
		)
	)
)

(instance bossReturns of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				([meep 3]
					posn: 130 114
					setLoop: 2
					cel: 2
					setPri: -1
					setCycle: CycleTo 0 -1
					setMotion: MoveTo 130 119 self
				)
			)
			(1
				(if (not local96_3_2)
					(meepHead init:)
					(= local96_3_2 1)
				)
				(meepHead posn: 130 110 z: 1 setPri: 8 setCycle: Forward)
				([meep 3] setPri: 8 setLoop: 1 cel: 0 stopUpd:)
				(if local301
					(BossSays 4 60 43)
					;"Hey, there ya go.  Hope it helps ya."
					(= local301 0)
				else
					(BossSays 4 60 44)
					;"Hey, sorry I couldn't help ya."
				)
				(= seconds 4)
			)
			(2
				(meepHead setCycle: 0 stopUpd:)
				(client setScript: 0)
			)
		)
	)
)

(instance throwObj of Script
	(method (changeState newState &tmp temp0 temp1 temp2 [temp3 2])
		(switch (= state newState)
			(0
				(throwSound play:)
				(= temp2 (Random 85 235))
				(= temp0 (+ 130 (SinMult temp2 local100_2_2)))
				(= temp1 (- 120 (CosMult temp2 local100_2_2)))
				(switch register
					(0
						((= newAct_3_2 (Actor new:)) view: vGreenMeep setLoop: 6)
					)
					(1
						(= babyOnScreen 1)
						(= newAct_3_2 babyMeep)
					)
					(2
						(= local301 (= furOnGround 1))
						(= newAct_3_2 greenFur)
					)
					(3
						(= local301 (= scrollOnGround 1))
						(= newAct_3_2 spellScroll)
					)
				)
				([meep 3] setCel: 1)
				(newAct_3_2
					setLoop: (newAct_3_2 loop?)
					cel: 0
					setPri: 7
					illegalBits: 0
					ignoreActors:
					posn: 133 110
					init:
					setCycle: 0
					setMotion: MoveTo temp0 temp1 self
				)
				(if (!= register 2) (newAct_3_2 setCycle: Forward))
			)
			(1
				(newAct_3_2 setCycle: 0 stopUpd:)
				(switch register
					(askFRUIT
						(newAct_3_2 addToPic:)
					)
					(askBABY
						(babyMeep setScript: bouncyBaby)
					)
				)
				([meep 3] setCel: 0)
				(client setScript: 0)
			)
		)
	)
)

(instance throwThings of Script
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(HandsOff)
				(= local100_2 1)
				(BossSays 6
					(Format
						@str 60 45
						[askedFor register]
						[askedFor local100_2]
						;Oh, you want %s?  I think I have %s somewhere around here.
					)
				)
				(if (ego inRect: 100 124 160 136)
					(ego setMotion: MoveTo 165 125 self)
				else
					(self cue:)
				)
			)
			(1
				(DirLoop ego (GetAngle (ego x?) (ego y?) 130 114))
				(= seconds 2)
			)
			(2
				(self setScript: bossHides self)
			)
			(3 (= seconds 2))
			(4
				(ego loop: 1)
				(if
					(or
						(> local300_3_2 20)
						(and (== register askFUR) (or furOnGround (Btst fGotFur)))
						(and
							(== register askSCROLL)
							(or scrollOnGround (Btst fLearnedDetect) (not [egoStats MAGIC]))
						)
					)
					(BossSays 5 60 46)
					;Gosh, I don't seem to have anything like that left.  Really sorry 'bout that.
					(= state 7)
					(= seconds 5)
				else
					(= local100_4 (Random 2 4))
					(if (== register 0)
						(= local100_4 0)
					)
					(= cycles 10)
				)
			)
			(5
				(if local100_4
					(= temp0 0)
					(if (and (not babyOnScreen) (== (Random 0 3) 1))
						(= temp0 1)
					)
					(if
						(and
							(== register 2)
							(not scrollOnGround)
							[egoStats 12]
							(not (Btst fLearnedDetect))
						)
						(= temp0 3)
					)
					(self setScript: throwObj self temp0)
				else
					(self changeState: 7)
				)
			)
			(6
				(-- local100_4)
				(++ local300_3_2)
				(= state 4)
				(= cycles 5)
			)
			(7
				(-- local100_2_2)
				(self setScript: throwObj self register)
			)
			(8
				(self setScript: bossReturns self)
			)
			(9
				(= local100_2 0)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance bossMad of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= bossOnScreen 0)
				(meepHead setCycle: Forward)
				(client
					setLoop: 4
					cel: 0
					cycleSpeed: 0
					setCycle: CycleTo 3 1 self
				)
			)
			(1
				(if local96_3_2 (= local96_3_2 0) (meepHead dispose:))
				(thudSound play:)
				(bossRock
					setCel: -1
					setCycle: Forward
					setPri: 8
					setMotion: MoveTo 140 54 self
				)
				(client setCycle: EndLoop)
			)
			(2
				(meepHead dispose:)
				(client
					setLoop: 2
					cel: -1
					setCycle: BegLoop
					setMotion: MoveTo 130 114 self
				)
				(bossRock setCycle: Forward setMotion: MoveTo 140 94)
			)
			(3
				(thudSound play:)
				(bossRock dispose:)
				(= local80_3_2 0)
				(client view: rMeepsPeep setLoop: 2 cel: 2 setCycle: BegLoop self)
				([eye 3]
					setLoop: (+ 3 (Random 0 1))
					show:
					setCycle: Forward
				)
				(= cycles 2)
			)
			(4
				([eye 3] setCycle: 0 stopUpd: hide:)
				(client setCycle: BegLoop self)
			)
			(5
				(if (not local300_3_2_2) (HandsOn))
				;EO: Fixed to prevent "Not an Object"
				(= [meepScript 3] (Clone aMeepScript))
				(client
					setLoop: 5
					cel: 0
					setPri: (+ 1 [meepPri 3])
					posn: [meepPosn 6] (- [meepPosn 7] 4)
					forceUpd:
					setScript: [meepScript 3] 0 ;(= [meepScript 3] (Clone aMeepScript))
				)
			)
		)
	)
)

(instance bouncyBaby of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setCycle: Forward)
				(self setScript: babySqueak)
				(= seconds 8)
			)
			(1
				(client setMotion: MoveTo 129 107 self)
			)
			(2 (= cycles 5))
			(3
				(= babyOnScreen 0)
				(client dispose:)
			)
		)
	)
)

(instance babySqueak of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles (Random 3 8))
				(babySound play:)
			)
			(1 (self changeState: 0))
		)
	)
)

(instance getThing of Script
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(= local300_3_2_2 1)
				(HandsOff)
				(ego ignoreBlocks: bossBlock)
				(if bossOnScreen
					([meep 3] setScript: bossMad self)
				else
					(self cue:)
				)
			)
			(1
				(= temp0
					(switch register
						(2 greenFur)
						(3 spellScroll)
					)
				)
				(ego
					illegalBits: 0
					ignoreActors:
					setMotion: MoveTo (+ (temp0 x?) 12) (temp0 y?) self
				)
			)
			(2
				(ego
					view: vEgoThrowing
					setLoop: 1
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop
				)
				(= cycles 8)
			)
			(3
				(switch register
					(2
						(greenFur dispose:)
						(ego get: iGreenFur)
						(= furOnGround FALSE)
						(Bset fGotFur)
					)
					(3
						(spellScroll dispose:)
						(ego get: iPaper)
						(= scrollOnGround FALSE)
						(Bset fLearnedDetect)
					)
				)
				(ego setCycle: BegLoop self)
			)
			(4
				(NormalEgo)
				(ego illegalBits: (| cWHITE cLGREY))
				(Face ego [meep 3])
				(switch register
					(askFUR
						(TimePrint 5 60 47)
						;You pick up the pile of soft green fur and carefully place it in your pack.
						(SolvePuzzle f60GetFur 5)
					)
					(askSCROLL
						(if [egoStats MAGIC]
							(TimePrint 8 60 48)
							;You pick up the Spell Scroll.  As the magical runes fade, you find you now know how to cast the "Detect Magic" spell.
							(ego learn: DETMAGIC 10)
							(SolvePuzzle f60LearnDetect 4 MAGIC_USER)
						else
							(TimePrint 5 60 49)
							;The magical runes fade from the paper before your eye can focus on them.
						)
					)
				)
				(ego observeBlocks: bossBlock)
				(HandsOn)
				(= local300_3_2_2 0)
				(self dispose:)
			)
		)
	)
)

(instance liftRock of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local300_3_2_2 1)
				(HandsOff)
				(= meepsScared TRUE)
				(if bossOnScreen
					([meep 3] setScript: bossMad self)
				else
					(self cue:)
				)
			)
			(1
				(ego
					ignoreActors: TRUE
					illegalBits: 0
					ignoreBlocks: bossBlock
					setMotion: MoveTo 130 106 self
				)
			)
			(2
				([meep 3] hide:)
				(ego
					view: vEgoPickupMeep
					setPri: 4
					setLoop: 0
					setCel: -1
					cel: 0
					cycleSpeed: 4
					setCycle: EndLoop self
				)
			)
			(3
				(ego setCycle: CycleTo 1 -1 self cycleSpeed: 5)
			)
			(4
				(ego setCycle: EndLoop self cycleSpeed: 2)
			)
			(5 (ego cel: 0) (= cycles 2))
			(6
				(thudSound play:)
				(ego setLoop: 1 cel: 0 cycleSpeed: 3 setCycle: EndLoop self)
				(ego posn: 130 103)
				([meep 3] show: view: rMeepsPeep setLoop: 5 setCel: 0)
			)
			(7
				(NormalEgo)
				(Face ego [meep 3])
				([meep 3] setLoop: -1 setCel: -1)
				(= meepsScared FALSE)
				(ego observeBlocks: bossBlock)
				(HandsOn)
				(= local300_3_2_2 0)
				(self dispose:)
			)
		)
	)
)
