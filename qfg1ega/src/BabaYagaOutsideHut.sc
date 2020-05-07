;;; Sierra Script 1.0 - (do not remove this comment)
(script# 22)
(include game.sh)
(use Main)
(use Intrface)
(use String)
(use LoadMany)
(use Extra)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm22 0
	hut 1
	walkEm 2
	flyAway 3
)

(local
	hutX
	hutY
	gateOpen
	crushedByHut
)
(procedure (EndPrint seconds &tmp [temp0 4] [temp4 400])
	(cls)
	(Format @temp4 &rest)
	(TextSize @[temp0 0] @temp4 userFont 0)
	(Print
		@temp4
		#at -1 130
		#width (if (> [temp0 2] 24) 300 else 0)
		#mode teJustCenter
		#dispose
		#time seconds
	)
)

(procedure (LookAround)
	(HighPrint 22 0)
	;Looking around, you get the feeling that this is not a very friendly place to be.
)

(procedure (MakeDeal)
	(HighPrint 22 1)
	;"All the other skulls have glowing eyes. But me?....noooo!  I don't need lovely glowing eyes!  Just open and close the gate, that's all I'm good for."
	(HighPrint 22 2)
	;"But if you can give me a glowing gem for my eyes, then I'll let you in the gate."
	(HighPrint 22 3)
	;"Well, is it a deal or isn't it?"
	(= yesNoTimer 100)
)

(procedure (RemindDeal)
	(HighPrint 22 4)
	;"You know the deal!  Get me a glowing gem for my eyes, and I'll let you in."
	(HighPrint 22 5)
	;"Do you agree to the deal?"
	(= yesNoTimer 100)
)

(instance wing1 of Actor
	(properties
		yStep 4
		view vBabaHut
		loop 8
		illegalBits $0000
		xStep 6
	)
)

(instance wing2 of Actor
	(properties
		yStep 4
		view vBabaHut
		loop 9
		illegalBits $0000
		xStep 6
	)
)

(instance hut of Actor
	(properties
		yStep 4
		view vBabaHut
		loop 4
		illegalBits $0000
		xStep 6
	)
)

(instance hutDoor of Actor
	(properties
		view vBabaHut
		loop 5
		cycleSpeed 1
		illegalBits $0000
	)
)

(instance frontLeg of Actor
	(properties
		yStep 4
		view vBabaHut
		illegalBits $0000
		xStep 6
	)
)

(instance frontFoot of Actor
	(properties
		yStep 4
		view vBabaHut
		loop 1
		cel 1
		illegalBits $0000
		xStep 6
	)
)

(instance backLeg of Actor
	(properties
		yStep 4
		view vBabaHut
		loop 2
		illegalBits $0000
		xStep 6
	)
)

(instance backFoot of Actor
	(properties
		yStep 4
		view vBabaHut
		loop 3
		cel 1
		illegalBits $0000
		xStep 6
	)
)

(instance gate of Actor
	(properties
		y 158
		x 191
		view vBonehead
		illegalBits $0000
	)
)

(instance dirt of Actor
	(properties
		y 156
		x 191
		view vBonehead
		loop 1
		illegalBits $0000
	)
)

(instance eyes of Actor
	(properties
		y 130
		x 189
		z 2
		view vBonehead
		loop 3
		cycleSpeed 1
		illegalBits $0000
	)
)

(instance skull1 of Extra
	(properties
		y 88
		x 117
		view vBonehead
		loop 4
		cycleType 1
		minPause 40
		maxPause 80
		minCycles 2
		maxCycles 4
	)
)

(instance skull2 of Extra
	(properties
		y 89
		x 251
		view vBonehead
		loop 4
		cycleType 1
		minPause 40
		maxPause 80
		minCycles 2
		maxCycles 4
	)
)

(instance skull3 of Extra
	(properties
		y 88
		x 72
		view vBonehead
		loop 6
		cycleType 1
		minPause 40
		maxPause 80
		minCycles 2
		maxCycles 4
	)
)

(instance skull4 of Extra
	(properties
		y 85
		x 14
		view vBonehead
		loop 5
		cycleType 1
		minPause 40
		maxPause 80
		minCycles 2
		maxCycles 4
	)
)

(instance skull5 of Extra
	(properties
		y 82
		x 295
		view vBonehead
		loop 5
		cycleType 1
		minPause 40
		maxPause 80
		minCycles 2
		maxCycles 4
	)
)

(instance TP of Actor
	(properties
		y 160
		x 152
		view vTeleportGreen
		cycleSpeed 1
		illegalBits $0000
	)
)

(instance tpSound of Sound
	(properties
		number 28
		priority 1
	)
)

(instance tromp of Sound
	(properties
		number 66
		priority 1
	)
)

(instance rm22 of Room
	(properties
		picture 22
		style $0000
		south 33
	)
	
	(method (init)
		(LoadMany VIEW vBabaHut vBonehead vFrogTransform vTeleportGreen vEgoDefeatedMagic)
		(LoadMany SOUND 23 (SoundFX 28) (SoundFX 66))
		(super init:)
		(keyDownHandler add: self)
		(StatusLine enable:)
		(NormalEgo)
		(= yesNoTimer 0)
		(cSound number: 23 loop: -1 play:)
		(if (not (Btst BABAYAGA_FLY_AWAY))
			(if (Btst BABAYAGA_HUT_OPEN)
				(= hutX 167)
				(= hutY 96)
				(hut
					setLoop: 4
					ignoreActors:
					posn: hutX hutY
					setPri: 4
					init:
					stopUpd:
				)
				(frontFoot
					setLoop: 1
					setPri: 3
					ignoreActors:
					posn: (- hutX 27) (- hutY 7)
					init:
					stopUpd:
				)
				(backFoot
					setLoop: 3
					setPri: 2
					ignoreActors:
					posn: (+ hutX 38) (- hutY 8)
					init:
					stopUpd:
				)
			else
				(= hutX 143)
				(= hutY 40)
				(frontLeg
					setLoop: 0
					setPri: 3
					ignoreActors:
					posn: (- hutX 20) hutY
					init:
					stopUpd:
				)
				(backLeg
					setLoop: 2
					setPri: 2
					ignoreActors:
					posn: (+ hutX 40) hutY
					init:
					stopUpd:
				)
				(frontFoot
					setLoop: 1
					setPri: 3
					ignoreActors:
					posn: (- hutX 27) (+ hutY 31)
					init:
					stopUpd:
				)
				(backFoot
					setLoop: 3
					setPri: 2
					ignoreActors:
					posn: (+ hutX 38) (+ hutY 32)
					init:
					stopUpd:
				)
				(gate setLoop: 0 setPri: 11 init: stopUpd:)
				(dirt ignoreActors: setPri: 11 init: stopUpd: hide:)
				(bonehead
					ignoreActors:
					setCycle: Forward
					setPri: 11
					init:
					stopUpd:
				)
				(eyes ignoreActors: setPri: 12 init: setCycle: Forward)
				(if (< hutState hutGAVEGEM) (eyes stopUpd: hide:))
				(hut
					setLoop: 4
					ignoreActors:
					posn: hutX hutY
					setPri: 4
					init:
					stopUpd:
				)
			)
		)
		(skull1 ignoreActors: setPri: 12 init:)
		(skull2 ignoreActors: setPri: 12 init:)
		(skull3 ignoreActors: setPri: 12 init:)
		(skull4 ignoreActors: setPri: 12 init:)
		(skull5 ignoreActors: setPri: 12 init:)
		(if (Btst BABAYAGA_FLY_AWAY)
			(skull1 stopUpd:)
			(skull2 stopUpd:)
			(skull3 stopUpd:)
			(skull4 stopUpd:)
			(skull5 stopUpd:)
		)
		(if (== prevRoomNum 21)
			(cond 
				((< babaState babaBRING)
					(ego view: vEgoFrogTransform loop: 2 cel: 7 posn: 152 160 ignoreActors:)
					(TP ignoreActors: setPri: 15 init: setScript: frogIn)
				)
				((== babaState babaFINALE) (hut setScript: flyAway))
				(else
					(ego posn: 152 160 loop: 2)
					(TP ignoreActors: setPri: 15 init: setScript: frogIn)
				)
			)
		else
			(ego posn: 225 189 init: setMotion: MoveTo 230 180)
		)
	)
	
	(method (doit)
		(if
			(and
				(ego inRect: 179 0 191 102)
				(Btst BABAYAGA_HUT_OPEN)
				(not crushedByHut)
				(< babaState babaFINALE)
			)
			(Bclr BABAYAGA_HUT_OPEN)
			(curRoom newRoom: 21)
		)
		(super doit:)
	)
	
	(method (dispose)
		(Bclr MET_BONEHEAD)
		(Bset VISITED_BABAYAGA_EXTERIOR)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp [temp0 50])
		(cond 
			((super handleEvent: event))
			((LookFor event {hut of brown})
				(cond 
					((or (not gateOpen) (Btst BABAYAGA_HUT_OPEN))
						(HighPrint 22 6)
						;Nothing happens.
					)
					(
					(StrFind (User inputLineAddr?) {now sit down})
						(SolvePuzzle POINTS_HUTSIT 7)
						(if (not (hut script?))
							(hut setScript: walkEm)
						else
							(Print 22 7 
								#title {HUT}
								#at -1 20)
							; "I'm moving as fast as I can!"
						)
					)
					(else (HighPrint 22 6)
						;Nothing happens.
					)
				)
			)
			((== (event type?) saidEvent)
				(cond 
					((Said 'look>')
						(cond 
							((Said '[<at,around][/!*]') (LookAround))
							((Said '/hut,house')
								(HighPrint 22 8)
								;The hut is a strange little house perched on chicken legs.
							)
							((Said '/leg,feet')
								(HighPrint 22 9)
								;Those are the biggest chicken legs you've ever seen.
							)
							((Said '/fence')
								(HighPrint 22 10)
								;The sharp ends of the fence are covered with some nasty-looking green slime.
							)
							((Said '/gate') (if gateOpen 
									(HighPrint 22 11)
									;The gate's gone.
									else
									(HighPrint 22 12)
									;The large skull on the gate seems to stare vacantly at you.
								)
							)
							((Said '/goo') (if (Btst VISITED_TAVERN_INSIDE)
									(HighPrint 22 13)
									;It looks a lot like the slime that was eating away the barstool at the tavern.
									else
									(HighPrint 22 14)
									;You've never seen anything like it.  It looks quite unhealthy.
								)
							)
							((Said '/skull,head')
								(HighPrint 22 15)
								;The skulls on top of the fence have eerily glowing eyes.
								(if (not gateOpen)
									(if (< hutState babaFINALE)
										(HighPrint 22 12)
										;The large skull on the gate seems to stare vacantly at you.
									else
										(HighPrint 22 16)
										;The eyes of the large skull on the gate flash merrily away at you.
									)
								)
							)
							((Said '/window')
								(HighPrint 22 17)
								;You can see nothing through the windows of the hut.
							)
							((Said '/door')
								(if (Btst BABAYAGA_HUT_OPEN)
									(HighPrint 22 18)
									;The door is open, but hardly inviting.
								else
									(HighPrint 22 19)
									;The door of the hut is ornately decorated.
								)
							)
							(
							(or (Said '/tree,bush,boulder')
								(Said '<up,down')
							)
							(HighPrint 22 20)
							;Your eyes are drawn to the incredible hut on chicken legs.
							)
						)
					)
					((Said 'nap,rest')
						(if (== (ego view?) 19)
							(HighPrint 22 21)
							;Now???
						else
							(event claimed: FALSE)
						)
					)
					((Said 'climb>')
						(cond 
							((Said '/fence,gate')
								(HighPrint 22 22)
								;The green slime dripping off of the fence top makes you realize that climbing is not a good idea.
							)
							((Said '/hut,leg,chicken')
								(cond 
									((Btst BABAYAGA_HUT_OPEN)
										(HighPrint 22 23)
										;What's to climb?
										)
									(gateOpen
										(HighPrint 22 24)
										;You have second thoughts, and you decide not to attempt such a potentially dangerous excursion.
										)
									(else
										(HighPrint 22 25)
										;You have to get there, first.
										)
								)
							)
							((Said '/boulder,cliff')
								(HighPrint 22 26)
								;Too much work.
							)
						)
					)
					((Said 'affirmative,n,please')
						(HighPrint 22 27)
						;Huh?
					)
					((Said 'say,recite,chat/rhyme,hut')
						(HighPrint 22 28)
						;Ok, go ahead and say the rhyme.
					)
				)
			)
		)
	)
)

(instance bonehead of Actor
	(properties
		y 137
		x 190
		z 1
		view vBonehead
		loop 2
		illegalBits $0000
	)
	
	(method (doit)
		(if
		(and (== (ego onControl: origin) cYELLOW) (not (Btst MET_BONEHEAD)))
			(Bset MET_BONEHEAD)
			(bonehead setScript: boneTalk)
		)
		(if (> yesNoTimer 1)
			(-- yesNoTimer)
		)
		(if (== yesNoTimer 1)
			(= yesNoTimer 0)
		)
		(super doit:)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(saidEvent
				(if (> yesNoTimer 1)
					(cond 
						(
						(or (Said 'affirmative,please,deal') (Said 'made/deal'))
							(= yesNoTimer 0)
							(switch hutState
								(hutINITIAL
									(HighPrint 22 27)
									;Huh?
									)
								(hutMETSKULL
									(HighPrint 22 29)
									;"The deal is made, then.  A glowing gem in exchange for an opportunity to see Baba Yaga herself."
									(= hutState 3)
									(SolvePuzzle 655 2)
								)
								(hutDEALMADE (HighPrint 22 30)
									;"Give me my gem!!"
									)
								(hutGAVEGEM
									(HighPrint 22 31)
									;"Hey, it's your life!"
									(gate setScript: openGate)
								)
							)
						)
						((Said 'n')
							(= yesNoTimer 0)
							(switch hutState
								(hutINITIAL (HighPrint 22 27)
									;Huh?
									)
								(hutMETSKULL
									(HighPrint 22 32)
									;"No gem, no entry.  That's the deal."
									(= hutState 2)
								)
								(hutNODEAL
									(HighPrint 22 32)
									;"No gem, no entry.  That's the deal."
									)
								(hutDEALMADE
									(HighPrint 22 33)
									;"What kind of hero are you?  Can't even find a little glowing gem for me to see with!"
									)
								(hutGAVEGEM
									(HighPrint 22 31)
									;"Hey, it's your life!"
									)
							)
						)
						(else
							(= yesNoTimer 80)
							(HighPrint 22 34)
							;"Just answer the question, will ya?"
							(switch hutState
								(hutINITIAL
									(HighPrint 22 27)
									;Huh?
									)
								(hutDEALMADE
									(HighPrint 22 35)
									;"Do you have my gem?"
									)
								(hutGAVEGEM
									(HighPrint 22 36)
									;"Do you want back in?"
									)
								(else
									(HighPrint 22 37)
									;"Is it a deal or not?"
									)
							)
							(event claimed: TRUE)
						)
					)
				)
				(cond 
					((Said 'chat') (bonehead setScript: boneTalk))
					((Said 'open[<gate]')
						(if (== hutState hutGAVEGEM)
							(HighPrint 22 38)
							;"Right!"
							(gate setScript: openGate)
						else
							(HighPrint 22 39)
							;"Not until you get my gem!"
						)
					)
					((Said 'ask>')
						(cond 
							((== hutState hutINITIAL)
								(event claimed: TRUE)
								(HighPrint 22 40)
								;"Hey!  Where's your manners?  Let me tell you something, here!"
								(bonehead setScript: boneTalk)
							)
							((Said '//deal')
								(switch hutState
									(hutINITIAL
										(HighPrint 22 41)
										;"Wait a minute...I'll get around to that."
										(bonehead setScript: boneTalk)
									)
									(hutMETSKULL (MakeDeal))
									(hutNODEAL (RemindDeal))
									(hutDEALMADE (RemindDeal))
									(hutGAVEGEM
										(HighPrint 22 42)
										;"You kept your end of the deal, all right!"
										)
								)
							)
							((Said '//baba') (if (Btst VISITED_BABAYAGA_EXTERIOR)
									(HighPrint 22 43)
									;"What's to tell?  You've seen her for yourself." 
									else
									(HighPrint 22 44)
									;"Baba Yaga is the most powerful Ogress around.
									;If you have any brains (and it looks like you don't), you'll stay away from her."
									)
								)
							((Said '//ogress')
								(HighPrint 22 45)
								;"Some hero YOU are!  Don't know what an Ogre is.  Just check out Baba Yaga!"
								)
							((Said '//gem')
								(switch hutState
									(hutINITIAL
										(bonehead setScript: boneTalk)
									)
									(hutMETSKULL (MakeDeal))
									(hutNODEAL (RemindDeal))
									(hutDEALMADE
										(HighPrint 22 46)
										;"All I ask for is a little gem or jewel that glows in the dark.   You're the big hero, so go find one."
										)
									(else
										(HighPrint 22 47)
										;"It's just the right color!"
										)
								)
							)
							((Said '//eye')
								(if (< hutState hutGAVEGEM)
									(HighPrint 22 48)
									;"I wish I had some."
								else
									(HighPrint 22 49)
									;"I'm thrilled to have some!"
								)
							)
							((Said '//skull')
								(HighPrint 22 50)
								;"The bone brains on top of the fence are Baba Yaga's spies.  That's why THEY have glowing eyes."
								)
							((Said '//gate')
								(HighPrint 22 51)
								;"It's not much, but I call it home."
								)
							((Said '//fence')
								(HighPrint 22 52)
								;"You've seen one, you've seen them all, I say."
								(HighPrint 22 53)
								;"Of course, most fences aren't poisoned on top!"
								)
							((Said '//hut,house')
								(HighPrint 22 54)
								;"Baba Yaga's hut will squat down if you say the rhyme."
								)
							((Said '//rhyme')
								(HighPrint 22 55)
								;"The rhyme is:  'Hut of brown, now sit down'."
								)
							(else (HighPrint 22 56)
								;"My time is important, and I'm very busy, as you can well see.  Ask me about something important."
								(event claimed: TRUE)
								)
						)
					)
					((Said 'gave[/eye,gem,skull][/skull,gem,eye]')
						(if (ego has: iMagicGem)
							(if (== (ego onControl: origin) cYELLOW)
								(SolvePuzzle POINTS_GIVEGEM 10)
								(bonehead setScript: putEyes)
							else
								(PrintNotCloseEnough)
							)
						else
							(PrintDontHaveIt)
						)
					)
				)
			)
		)
	)
)

(instance putEyes of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ChangeGait MOVE_WALK FALSE)
				(ego illegalBits: 0 setMotion: MoveTo 189 165 self)
			)
			(1
				(ego loop: 3 forceUpd:)
				(= cycles 2)
			)
			(2
				(HighPrint 22 57 25 4) ;EO: What are the two extra tuples for?
				;You place the glowing gem inside the skull.
				(ego use: iMagicGem)
				(eyes startUpd: show:)
				(= hutState hutGAVEGEM)
				(self cue:)
			)
			(3
				(ego setMotion: MoveTo 200 175 self)
			)
			(4
				(NormalEgo)
				(ego loop: 3 forceUpd:)
				(= seconds 4)
			)
			(5
				(Bset GAVE_GEM_BONEHEAD)
				(HighPrint 22 58)
				;"I can see!  I have eyes again!!"
				(HighPrint 22 59)
				;"Yeccchhh!  Is THAT what you look like???"
				(HighPrint 22 60)
				;"Oh well!  Have fun visiting Baba Yaga.  And good luck....you'll need it!"
				(self cue:)
			)
			(6 (gate setScript: openGate))
		)
	)
)

(instance opener of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 15))
			(1
				(if (or (not (Btst VISITED_BABAYAGA_EXTERIOR)) (!= prevRoomNum 0))
					(LookAround)
				)
				(client setScript: 0)
			)
		)
	)
)

(instance boneTalk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(bonehead startUpd:)
				(= cycles 10)
			)
			(1
				(switch hutState
					(hutINITIAL
						(= hutState hutMETSKULL)
						(HighPrint 22 61)
						;"Baba Yaga does not welcome strangers!  You have to deal with me before you can enter."
					)
					(hutMETSKULL (HighPrint 22 62)
						;"Baba Yaga is one tough magic user.  If you're so stupid that you want to enter, perhaps we can make a deal."
						)
					(hutNODEAL
						(HighPrint 22 63)
						;"So you're back!  Changed your mind about making a little deal, hero?"
						(= yesNoTimer 100)
					)
					(hutDEALMADE
						(HighPrint 22 64)
						;"Did you get it?  Did you get my glowing gem???"
						(= yesNoTimer 100)
					)
					(else 
						(if (and (Btst VISITED_BABAYAGA_INTERIOR) (ego has: iMandrake))
							(HighPrint 22 65)
							;"So you made it back, did you?  She is expecting you."
							(gate setScript: openGate)
						else
							(HighPrint 22 66)
							;"You again??  Do you really want to go back in there?"
							(= yesNoTimer 100)
						)
					)
				)
				(self cue:)
			)
			(2 (= cycles 10))
			(3
				(HandsOn)
				(bonehead cel: 0 stopUpd: setScript: 0)
				(if (and (ego has: iMandrake) (Btst GAVE_GEM_BONEHEAD))
					(gate setScript: openGate)
				)
			)
		)
	)
)

(instance openGate of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(bonehead dispose:)
				(eyes dispose:)
				(gate ignoreActors: setPri: 1 setMotion: MoveTo 191 230)
				(dirt show: setCycle: Forward startUpd:)
				(= cycles 10)
			)
			(1
				(if (not (Btst VISITED_BABAYAGA_INTERIOR))
					(HighPrint 22 67)
					;"I hope you can remember the rhyme."
				else
					(HighPrint 22 68)
					;Bye!
				)
				(self cue:)
			)
			(2
				(gate setMotion: MoveTo 191 230 self)
			)
			(3
				(gate stopUpd:)
				(= gateOpen TRUE)
				(dirt setCycle: 0 setCel: 0 stopUpd:)
				(HandsOn)
			)
		)
	)
)

(instance walkEm of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(User canInput: FALSE)
				(frontLeg setCel: 1)
				(frontFoot
					setCel: 0
					posn: (+ (frontFoot x?) 5) (- (frontFoot y?) 5)
				)
				(= cycles 2)
			)
			(1
				(frontLeg
					setMotion: MoveTo (+ (frontLeg x?) 12) (+ (frontLeg y?) 8) self
				)
				(frontFoot
					setMotion: MoveTo (+ (frontFoot x?) 12) (+ (frontFoot y?) 8)
				)
			)
			(2
				(frontLeg
					setMotion: MoveTo (+ (frontLeg x?) 12) (+ (frontLeg y?) 8) self
				)
				(frontFoot
					setMotion: MoveTo (+ (frontFoot x?) 12) (+ (frontFoot y?) 8)
				)
				(hut setMotion: MoveTo (+ (hut x?) 12) (+ (hut y?) 8))
			)
			(3
				(frontLeg setCel: 0)
				(frontFoot
					setCel: 1
					posn: (- (frontFoot x?) 5) (+ (frontFoot y?) 5)
				)
				(hut setMotion: MoveTo (+ (hut x?) 12) (+ (hut y?) 8))
				(backLeg setCel: 1)
				(backFoot
					setCel: 0
					posn: (+ (backFoot x?) 5) (- (backFoot y?) 5)
				)
				(if (ego inRect: 123 0 235 103)
					(HandsOff)
					(ego
						view: vEgoDefeatedMagic
						setLoop: 3
						cel: 0
						cycleSpeed: 1
						setCycle: EndLoop
					)
					(= crushedByHut TRUE)
				)
				(= cycles 2)
			)
			(4
				(tromp number: (SoundFX 66) init: play:)
				(ShakeScreen 3)
				(backLeg
					setMotion: MoveTo (+ (backLeg x?) 24) (+ (backLeg y?) 16) self
				)
				(backFoot
					setMotion: MoveTo (+ (backFoot x?) 24) (+ (backFoot y?) 16)
				)
			)
			(5
				(backLeg setCel: 0)
				(backFoot
					setCel: 1
					posn: (- (backFoot x?) 5) (+ (backFoot y?) 5)
				)
				(= cycles 2)
			)
			(6
				(tromp play:)
				(ShakeScreen 3)
				(= cycles 2)
			)
			(7
				(frontLeg
					setCel: 1
					posn: (- (frontLeg x?) 5) (+ (frontLeg y?) 5)
				)
				(backLeg
					setCel: 1
					posn: (- (backLeg x?) 5) (+ (backLeg y?) 5)
				)
				(hut posn: (hut x?) (+ (hut y?) 10))
				(= cycles 1)
			)
			(8
				(frontLeg
					setCel: 2
					posn: (- (frontLeg x?) 2) (+ (frontLeg y?) 10)
				)
				(backLeg
					setCel: 2
					posn: (- (backLeg x?) 3) (+ (backLeg y?) 9)
				)
				(hut posn: (hut x?) (+ (hut y?) 10))
				(= cycles 1)
			)
			(9
				(frontLeg
					setCel: 2
					posn: (- (frontLeg x?) 1) (+ (frontLeg y?) 2)
				)
				(backLeg
					setCel: 2
					posn: (- (backLeg x?) 1) (+ (backLeg y?) 2)
				)
				(hut posn: (hut x?) (+ (hut y?) 10))
				(if crushedByHut (ego setPri: 3))
				(= cycles 1)
			)
			(10
				(hut posn: (hut x?) (+ (hut y?) 10))
				(self cue:)
			)
			(11
				(= hutX (hut x?))
				(= hutY (hut y?))
				(hut stopUpd: ignoreActors: 0)
				(frontLeg dispose:)
				(backLeg dispose:)
				(frontFoot stopUpd:)
				(backFoot stopUpd:)
				(if crushedByHut (ego dispose:))
				(= cycles 1)
			)
			(12
				(Bset BABAYAGA_HUT_OPEN)
				(tromp play:)
				(ShakeScreen 6 shakeSDiagonal)
				(= cycles 6)
			)
			(13
				(hutDoor
					ignoreActors:
					posn: (+ (hut x?) 17) (- (hut y?) 5)
					cel: 0
					init:
					setCycle: EndLoop self
				)
			)
			(14
				(HandsOn)
				(hutDoor stopUpd:)
				(hut setScript: 0)
				(if crushedByHut
					(EgoDead 22 69
						#title { Bad strategy!_}
						#icon vEgoDefeatedMagic 3 7)
				)
			)
		)
	)
)

(instance flyAway of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(skull1 stopUpd: addToPic:)
				(skull2 stopUpd: addToPic:)
				(skull3 stopUpd: addToPic:)
				(skull4 stopUpd: addToPic:)
				(skull5 stopUpd: addToPic:)
				(if (cast contains: gate) (gate addToPic:))
				(if (cast contains: dirt) (dirt addToPic:))
				(if (cast contains: bonehead) (bonehead addToPic:))
				(if (cast contains: eyes) (eyes addToPic:))
				(TP
					ignoreActors:
					setPri: 15
					posn: 187 130
					init:
					setCycle: CycleTo 6 1 self
				)
			)
			(1
				(tpSound number: (SoundFX 28) init: play:)
				(TP setCycle: EndLoop)
				(ego posn: 187 130 init:)
				(hutDoor
					ignoreActors:
					posn: (+ (hut x?) 17) (- (hut y?) 5)
					cel: 2
					init:
					setPri: 4
					setCycle: BegLoop self
				)
				(Face ego hutDoor)
			)
			(2
				(wing1
					ignoreActors:
					ignoreHorizon:
					setPri: 10
					posn: (- hutX 36) (- hutY 57)
					init:
					setCycle: EndLoop
				)
				(wing2
					ignoreActors:
					ignoreHorizon:
					posn: (+ hutX 46) (- hutY 52)
					init:
					setCycle: EndLoop self
				)
			)
			(3
				(hutDoor dispose:)
				(wing1 setLoop: 6 setCycle: Forward)
				(wing2 setLoop: 7 setCycle: Forward)
				(= cycles 12)
			)
			(4
				(ego loop: 3 forceUpd:)
				(EndPrint 8 22 70)
				;There goes Baba Yaga.  Now you've REALLY made her soar!
				(hut setMotion: MoveTo hutX -100)
				(wing1 setMotion: MoveTo (wing1 x?) -43)
				(wing2 setMotion: MoveTo (wing2 x?) -58)
				(frontFoot
					ignoreHorizon:
					setMotion: MoveTo (frontFoot x?) -58
				)
				(backFoot
					ignoreHorizon:
					setMotion: MoveTo (backFoot x?) -58
				)
				(= seconds 8)
			)
			(5
				(Bset BABAYAGA_FLY_AWAY)
				(Bclr BABAYAGA_HUT_OPEN)
				(wing1 dispose:)
				(wing2 dispose:)
				(frontFoot dispose:)
				(backFoot dispose:)
				(hut dispose:)
				(curRoom newRoom: 600)
			)
		)
	)
)

(instance frogIn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(TP setCycle: CycleTo 6 1 self)
			)
			(1
				(tpSound number: (SoundFX 28) init: play:)
				(ego init:)
				(TP setCycle: EndLoop)
				(= cycles 10)
			)
			(2
				(if (>= babaState babaBRING)
					(self changeState: 5)
				else
					(HighPrint 22 71)
					;From a distance, you hear the witch intone:
					(HighPrint 22 72)
					;"Oops!  I very nearly forgot..."
					(Print 22 73
						#at -1 12
						#width 145
						#mode teJustCenter
						#time 8)
					(= seconds 3)
					; "Hear me, oh Powers
; Of Mana and More:
; Turn this fool back
; As he was before."
				)
			)
			(3
				(tpSound play:)
				(ego setCycle: BegLoop self)
			)
			(4
				(NormalEgo)
				(ego loop: 1)
				(= cycles 10)
			)
			(5
				(HandsOn)
				(switch babaState
					(babaFETCH
						(HighPrint 22 74)
						;As you contemplate the occurrences of the last few minutes, you conclude that you'd better find her mandrake root...and fast!
						(Bset fBabaCurse)
						(= dayCursedByBabaYaga Day)
					)
					(babaBRING (HighPrint 22 75)
						;"What an awful creature, to turn me into a FROG!", you say to yourself.  "Someday, I must return the favor."
						)
				)
				(TP dispose:)
			)
		)
	)
)
