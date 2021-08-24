;;; Sierra Script 1.0 - (do not remove this comment)
(script# 73)
(include game.sh)
(use Main)
(use CastDart)
(use ThrowKnife)
(use ThrowRock)
(use CastDazz)
(use TalkObj)
(use Target)
(use RFeature)
(use Save)
(use Motion)
(use Game)
(use Invent)
(use User)
(use Actor)
(use System)

(public
	rm73 0
)

(local
	brigandMeeting
	brutusPreCombat
	brutusDead
	brutusPostCombat
	brutusThrowingDaggersAtEgo
	local5
	armorProtection
	daggersOnTarget
	local8 =  55
	oldEgoY
	local10
	brutusHostile
	local12
	daggerBeingThrown
)
(procedure (RetrieveDaggers param1 &tmp temp0)
	(= temp0 0)
	(cond 
		(
			(and
				(== curRoomNum daggerRoom)
				(or daggersOnTarget missedDaggers hitDaggers [invDropped iDagger])
			)
			(ego
				get: iDagger (+ daggersOnTarget missedDaggers hitDaggers [invDropped iDagger])
			)
			(HighPrint 73 0)
			;You retrieve your daggers.
			(= temp0 daggersOnTarget)
		)
		(param1
			(HighPrint 73 1)
			;There aren't any loose daggers here.
		)
	)
	(= [invDropped iDagger]
		(= daggersOnTarget
			(= hitDaggers
				(= missedDaggers (= daggerRoom 0))
			)
		)
	)
	(return temp0)
)

(instance dags of Set)

(instance knife1 of Actor
	(properties
		view vBruno
	)
)

(instance knife2 of Actor
	(properties
		view vBruno
	)
)

(instance shield of RPicView
	(properties
		y 132
		x 140
		view vBrutus
		loop 8
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(or
					(MouseClaimed self event shiftDown)
					(Said 'look/stuff,shield,spear')
				)
				(HighPrint 73 2)
				;The objects on the ground are a brigand's shield and spear.
			)
		)
	)
)

(instance brunoWin of SysWindow
	(properties
		color vGREY
		title {Bruno:}
	)
	
	(method (open &tmp vDiff)
		(-= top (= vDiff (- top 12)))
		(-= bottom vDiff)
		(super open:)
	)
)

(instance brutusWin of SysWindow
	(properties
		color vBLUE
		title {Brutus:}
	)
	
	(method (open &tmp vDiff)
		(-= top (= vDiff (- top 12)))
		(-= bottom vDiff)
		(super open:)
	)
)

(instance brunoTalk of TalkObj
	(properties
		tLoop 4
	)
)

(instance brutusTalk of TalkObj
	(properties
		tLoop 4
		cSpeed 4
	)
)

(instance brutus of TargActor
	(properties
		y 114
		x 136
		view vBrutus
		loop 4
	)
	
	(method (doit)
		(brutusTalk doit:)
		(super doit:)
	)
	
	(method (handleEvent event)
		(cond 
			(brutusHostile (event claimed: TRUE))
			((super handleEvent: event))
			(
				(or
					(Said 'look/brutus,man,thief,bandit,body')
					(MouseClaimed self event shiftDown)
				)
				(if (Btst fBeatBrutus)
					(HighPrint 73 3)
					;The dead brigand lies very still.
					 else
					 (HighPrint 73 4)
					 ;You see a hard-looking character who appears to be a thief.  He must be one of the Brigands!
				 )
			)
			((Said 'get/shield')
				(if (Btst fBeatBrutus)
					(HighPrint 73 5)
					;The dead brigand's shield is not worth your while.
				else
					(HighPrint 73 6)
					;You're kidding, right?
					)
				)
			(
				(or
					(Said 'get/key')
					(Said 'search/bandit,man,thief,brutus,body')
				)
				(if (Btst fBeatBrutus)
					(if (ego inRect: 90 92 230 188)
						(ego setScript: egoSearch)
					else
						(HighPrint 73 7)
						;Get closer to him.
					)
				else
					(HighPrint 73 6)
					;You're kidding, right?
				)
			)
			(else
				(brutusTalk handleEvent: event)
			)
		)
	)
	
	(method (getHurt damage)
		(if
			(and
				(<= (-= monsterHealth damage) 0)
				(not (Btst fBeatBrutus))
			)
			(= zapPower 0)
			(= monsterNum 0)
			(Bset fBeatBrutus)
			(self setScript: brutusDies)
		)
		(= brutusHealth monsterHealth)
	)
)

(instance bruno of Actor
	(properties
		y 117
		x 102
		view vBruno
		loop 4
	)
	
	(method (doit)
		(brunoTalk doit:)
		(super doit:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(or
					(Said 'look/bruno')
					(MouseClaimed self event shiftDown)
				)
				(HighPrint 73 8)
				;This man looks very tough.  From his clothing, you guess him to be a member of the Thieves' Guild.
			)
			(else
				(brunoTalk handleEvent: event)
			)
		)
	)
)

(instance target of RPicView
	(properties
		y 105
		x 187
		view vThrowingRange
		priority 1
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(or
					(MouseClaimed self event shiftDown)
					(Said 'look/target,board,sign')
				)
				(if daggersOnTarget
					(HighPrint 73 9)
					;You are using the old archery target for dagger practice.
					else (HighPrint 73 10)
					;The old archery target looks as though it has not been used in quite some time.
				)
			)
		)
	)
)

(instance rm73 of Room
	(properties
		picture 73
		style IRISOUT
		east 74
		south 80
		west 72
	)
	
	;CI: NOTE: This is a manual disassembly of the asm. The original has been commented out below.
	(method (init param1)
		(Load VIEW vThrowingRange)
		(Load SOUND (SoundFX 31))
		(Load SCRIPT 101)
		(StatusLine enable:)
		(super init: param1)
		(self setLocales: FOREST)
		
		(cond
			((= brigandMeeting (and (Btst fBearGone) (not (Btst fSpiedOnThieves)) (== timeODay TIME_MIDDAY) (or (== prevRoomNum 72) (== prevRoomNum 74))))
				(= brutusHealth MAX_HP_BRUTUS)
				(= monsterHealth brutusHealth)
				(= monsterNum vBrigand)
				(Load VIEW vBruno)
				(Load VIEW vBrutus)
			)
			((= brutusDead (and brunoTimer (Btst fBeatBrutus)))
				(Load VIEW vBrutus)
			)
			((= brutusThrowingDaggersAtEgo (and brunoTimer (or (== prevRoomNum 72) (== prevRoomNum 74)) (not (Btst fBeatBrutus))))
				(Load VIEW vBrutus)
				(= monsterHealth brutusHealth)
				(= monsterNum vBrigand)
			)
			((= brutusPreCombat (and brunoTimer (== prevRoomNum 80) (not (Btst fBeatBrutus))))
				(Load VIEW vBrutus)
				(= monsterHealth brutusHealth)
				(= monsterNum vBrigand)
				(= brigandHead 6)
				(brutus posn: 134 120)
			)
			((= brutusPostCombat (== prevRoomNum vBrigand))
				(Bset fBeatBrutus)
				(Load VIEW vBrigandDefeated)
			)
		)
		
		(if (or brutusThrowingDaggersAtEgo brutusPreCombat brigandMeeting)
			(shield init:)
			(addToPics add: shield doit:)
			(features add: shield)
		)

		(target init:)
		(addToPics add: target doit:)
		(features add: target)
		(NormalEgo)
		(ego init:)
		(= armorProtection 0)
		(if (ego has: iChainmail)
			(= armorProtection CHAIN_VALUE)
		)
		(if (ego has: iLeather)
			(= armorProtection LEATHER_VALUE)
		)
		
		(switch prevRoomNum
			(72
				(ego posn: 2 160 setMotion: MoveTo 57 146)
				(= local5 -1) ;$FFFF
			)
			(74
				(ego posn: 319 140 setMotion: MoveTo 276 133)
				(= local5 1)
			)
			(80
				(ego posn: 160 187 setMotion: MoveTo 160 170)
				(= local5 0)
			)
			(else
				(ego posn: 165 108 setMotion: MoveTo 165 125)
			)
		)

		;CI: NOTE: I believe the original asm had this before the switch. 
		;I've placed it here, because it appears to make more sense
		(brutus targDeltaX: 
			(if (== local5 1) 
				12 
			else 
				(if (== local5 -1)
					-12
				)
			)
		)
		
		(cond
			(brigandMeeting
				(self setScript: brigsMeet)
			)
			(brutusThrowingDaggersAtEgo
				(brutus init: setScript: brutusThrows)
			)
			(brutusPreCombat
				(self setScript: brutusLives)
			)
			(brutusDead
				(brutus view: vBrigandDefeated loop: 0 cel: 7 init: stopUpd:)
				(dags add:)
				(self setScript: nobodyHere)
				
			)
			(brutusPostCombat
				(= monsterNum NULL)
				(ego loop: 3 posn: 140 124)
				(self setScript: brutusDies)
			)
			(else
				(dags add:)
				(self setScript: nobodyHere)
			)
		)
	)
	
;;;	(method (init param1)
;;;		(asm
;;;			pushi    2
;;;			pushi    RES_VIEW
;;;			pushi    vThrowingRange
;;;			callk    Load,  4
;;;			pushi    2
;;;			pushi    RES_SOUND
;;;			pushi    1
;;;			pushi    31
;;;			callb    GetSongNumber,  2
;;;			push    
;;;			callk    Load,  4
;;;			pushi    2
;;;			pushi    RES_SCRIPT
;;;			pushi    101
;;;			callk    Load,  4
;;;			pushi    #enable
;;;			pushi    0
;;;			class    StatusLine
;;;			send     4
;;;			pushi    #init
;;;			pushi    0
;;;			&rest    param1
;;;			super    Room,  4
;;;			pushi    #setLocales
;;;			pushi    1
;;;			pushi    FOREST
;;;			self     6
;;;			pushi    1
;;;			pushi    fBearGone
;;;			callb    Btst,  2
;;;			bnt      code_0698
;;;			pushi    1
;;;			pushi    fSpiedOnThieves
;;;			callb    Btst,  2
;;;			not     
;;;			bnt      code_0698
;;;			lsg      timeZone
;;;			ldi      2
;;;			eq?     
;;;			bnt      code_0698
;;;			lsg      prevRoomNum
;;;			ldi      72
;;;			eq?     
;;;			bt       code_0698
;;;			lsg      prevRoomNum
;;;			ldi      74
;;;			eq?     
;;;code_0698:
;;;			sal      brigandMeeting
;;;			bnt      code_06c0
;;;			ldi      MAX_HP_BRUTUS
;;;			sag      brutusHealth
;;;			sag      monsterHealth
;;;			ldi      vBrigand
;;;			sag      monsterNum
;;;			pushi    2
;;;			pushi    RES_VIEW
;;;			pushi    vBruno
;;;			callk    Load,  4
;;;			pushi    2
;;;			pushi    RES_VIEW
;;;			pushi    vBrutus
;;;			callk    Load,  4
;;;			jmp      code_077b
;;;code_06c0:
;;;			lag      brunoTimer
;;;			bnt      code_06cd
;;;			pushi    1
;;;			pushi    fBeatBrutus
;;;			callb    Btst,  2
;;;code_06cd:
;;;			sal      brutusDead
;;;			bnt      code_06de
;;;			pushi    2
;;;			pushi    RES_VIEW
;;;			pushi    vBrutus
;;;			callk    Load,  4
;;;			jmp      code_077b
;;;code_06de:
;;;			lag      brunoTimer
;;;			bnt      code_06fc
;;;			lsg      prevRoomNum
;;;			ldi      72
;;;			eq?     
;;;			bt       code_06f4
;;;			lsg      prevRoomNum
;;;			ldi      74
;;;			eq?     
;;;			bnt      code_06fc
;;;code_06f4:
;;;			pushi    1
;;;			pushi    fBeatBrutus
;;;			callb    Btst,  2
;;;			not     
;;;code_06fc:
;;;			sal      brutusThrowingDaggersAtEgo
;;;			bnt      code_0719
;;;			pushi    2
;;;			pushi    RES_VIEW
;;;			pushi    vBrutus
;;;			callk    Load,  4
;;;			lag      brutusHealth
;;;			sag      monsterHealth
;;;			ldi      vBrigand
;;;			sag      monsterNum
;;;			jmp      code_077b
;;;code_0719:
;;;			lag      brunoTimer
;;;			bnt      code_072f
;;;			lsg      prevRoomNum
;;;			ldi      80
;;;			eq?     
;;;			bnt      code_072f
;;;			pushi    1
;;;			pushi    fBeatBrutus
;;;			callb    Btst,  2
;;;			not     
;;;code_072f:
;;;			sal      brutusPreCombat
;;;			bnt      code_075f
;;;			pushi    2
;;;			pushi    RES_VIEW
;;;			pushi    vBrutus
;;;			callk    Load,  4
;;;			lag      brutusHealth
;;;			sag      monsterHealth
;;;			ldi      vBrigand
;;;			sag      monsterNum
;;;			ldi      6
;;;			sag      brigandHead
;;;			pushi    #posn
;;;			pushi    2
;;;			pushi    134
;;;			pushi    120
;;;			lofsa    brutus
;;;			send     8
;;;			jmp      code_077b
;;;code_075f:
;;;			lsg      prevRoomNum
;;;			ldi      vBrigand
;;;			eq?     
;;;			sal      brutusPostCombat
;;;			bnt      code_077b
;;;			pushi    1
;;;			pushi    fBeatBrutus
;;;			callb    Bset,  2
;;;			pushi    2
;;;			pushi    RES_VIEW
;;;			pushi    vBrigandDefeated
;;;			callk    Load,  4
;;;code_077b:
;;;			lal      brutusThrowingDaggersAtEgo
;;;			bt       code_078a
;;;			lal      brutusPreCombat
;;;			bt       code_078a
;;;			lal      brigandMeeting
;;;			bnt      code_07ab
;;;code_078a:
;;;			pushi    #init
;;;			pushi    0
;;;			lofsa    shield
;;;			send     4
;;;
;;;			pushi    #add
;;;			pushi    1
;;;			lofsa    shield
;;;			push    
;;;			pushi    #doit
;;;			pushi    0
;;;			lag      addToPics
;;;			send     10
;;;
;;;			pushi    #add
;;;			pushi    1
;;;			lofsa    shield
;;;			push    
;;;			lag      features
;;;			send     6
;;;code_07ab:
;;;			pushi    #init
;;;			pushi    0
;;;			lofsa    target
;;;			send     4
;;;
;;;			pushi    #add
;;;			pushi    1
;;;			lofsa    target
;;;			push    
;;;			pushi    #doit
;;;			pushi    0
;;;			lag      addToPics
;;;			send     10
;;;
;;;			pushi    #add
;;;			pushi    1
;;;			lofsa    target
;;;			push    
;;;			lag      features
;;;			send     6
;;;
;;;			pushi    0
;;;			callb    StopEgo,  0
;;;			pushi    #init
;;;			pushi    0
;;;			lag      ego
;;;			send     4
;;;
;;;			ldi      0
;;;			sal      armorProtection
;;;			pushi    #has
;;;			pushi    1
;;;			pushi    iChainmail
;;;			lag      ego
;;;			send     6
;;;
;;;			bnt      code_07ef
;;;			ldi      5
;;;			sal      armorProtection
;;;			jmp      code_0800
;;;code_07ef:
;;;			pushi    #has
;;;			pushi    1
;;;			pushi    iLeather
;;;			lag      ego
;;;			send     6
;;;			bnt      code_0800
;;;			ldi      3
;;;			sal      armorProtection
;;;code_0800:
;;;			pushi    #targDeltaX
;;;			pushi    1
;;;			lsl      local5
;;;			dup     
;;;			ldi      1
;;;			eq?     
;;;			bnt      code_0812
;;;			ldi      12
;;;			jmp      code_081b
;;;code_0812:
;;;			dup     
;;;			ldi      $FFFF			;-1
;;;			eq?     
;;;			bnt      code_081b
;;;			ldi      $FFF4			;-12
;;;code_081b:
;;;			toss    
;;;			push    
;;;			lofsa    brutus
;;;			send     6
;;;			lsg      prevRoomNum
;;;			dup     
;;;			ldi      72
;;;			eq?     
;;;			bnt      code_084b
;;;			pushi    #posn
;;;			pushi    2
;;;			pushi    2
;;;			pushi    160
;;;			pushi    #setMotion
;;;			pushi    3
;;;			class    MoveTo
;;;			push    
;;;			pushi    57
;;;			pushi    146
;;;			lag      ego
;;;			send     18
;;;			ldi      $FFFF			;-1
;;;			sal      local5
;;;			jmp      code_08b9
;;;code_084b:
;;;			dup     
;;;			ldi      74
;;;			eq?     
;;;			bnt      code_0875
;;;			pushi    #posn
;;;			pushi    2
;;;			pushi    319
;;;			pushi    140
;;;			pushi    #setMotion
;;;			pushi    3
;;;			class    MoveTo
;;;			push    
;;;			pushi    276
;;;			pushi    133
;;;			lag      ego
;;;			send     18
;;;			ldi      1
;;;			sal      local5
;;;			jmp      code_08b9
;;;code_0875:
;;;			dup     
;;;			ldi      80
;;;			eq?     
;;;			bnt      code_089f
;;;			pushi    #posn
;;;			pushi    2
;;;			pushi    160
;;;			pushi    187
;;;			pushi    #setMotion
;;;			pushi    3
;;;			class    MoveTo
;;;			push    
;;;			pushi    160
;;;			pushi    170
;;;			lag      ego
;;;			send     18
;;;			ldi      0
;;;			sal      local5
;;;			jmp      code_08b9
;;;code_089f:
;;;			pushi    #posn
;;;			pushi    2
;;;			pushi    165
;;;			pushi    108
;;;			pushi    #setMotion
;;;			pushi    3
;;;			class    MoveTo
;;;			push    
;;;			pushi    165
;;;			pushi    125
;;;			lag      ego
;;;			send     18
;;;code_08b9:
;;;			toss    
;;;			lal      brigandMeeting
;;;			bnt      code_08cb
;;;			pushi    #setScript
;;;			pushi    1
;;;			lofsa    brigsMeet
;;;			push    
;;;			self     6
;;;			jmp      code_0960
;;;code_08cb:
;;;			lal      brutusThrowingDaggersAtEgo
;;;			bnt      code_08e2
;;;			pushi    #init
;;;			pushi    0
;;;			pushi    #setScript
;;;			pushi    1
;;;			lofsa    brutusThrows
;;;			push    
;;;			lofsa    brutus
;;;			send     10
;;;			jmp      code_0960
;;;code_08e2:
;;;			lal      brutusPreCombat
;;;			bnt      code_08f3
;;;			pushi    #setScript
;;;			pushi    1
;;;			lofsa    brutusLives
;;;			push    
;;;			self     6
;;;			jmp      code_0960
;;;code_08f3:
;;;			lal      brutusDead
;;;			bnt      code_0927
;;;			pushi    #view
;;;			pushi    1
;;;			pushi    vBrigandDefeated
;;;			pushi    #loop
;;;			pushi    1
;;;			pushi    0
;;;			pushi    #cel
;;;			pushi    1
;;;			pushi    7
;;;			pushi    #init
;;;			pushi    0
;;;			pushi    #stopUpd
;;;			pushi    0
;;;			lofsa    brutus
;;;			send     26
;;;			pushi    #add
;;;			pushi    0
;;;			lofsa    dags
;;;			send     4
;;;			pushi    #setScript
;;;			pushi    1
;;;			lofsa    nobodyHere
;;;			push    
;;;			self     6
;;;			jmp      code_0960
;;;code_0927:
;;;			lal      brutusPostCombat
;;;			bnt      code_094f
;;;			ldi      0
;;;			sag      monsterNum
;;;			pushi    #loop
;;;			pushi    1
;;;			pushi    3
;;;			pushi    #posn
;;;			pushi    2
;;;			pushi    140
;;;			pushi    124
;;;			lag      ego
;;;			send     14
;;;			pushi    #setScript
;;;			pushi    1
;;;			lofsa    brutusDies
;;;			push    
;;;			self     6
;;;			jmp      code_0960
;;;code_094f:
;;;			pushi    #add
;;;			pushi    0
;;;			lofsa    dags
;;;			send     4
;;;			pushi    #setScript
;;;			pushi    1
;;;			lofsa    nobodyHere
;;;			push    
;;;			self     6
;;;code_0960:
;;;			ret     
;;;		)
;;;	)
	
	(method (dispose)
		(dags eachElementDo: #dispose 84 release:)
		(dags dispose:)
		(if (!= newRoomNum vBrigand)
			(= monsterNum FALSE)
			(= brigandHead 0)
		)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp spell theBrutus)
		(switch (event type?)
			(saidEvent
				(cond 
					((super handleEvent: event))
					((Said 'look/dagger')
						(if daggersOnTarget (HighPrint 73 11)
							;The only daggers around here are the ones you've thrown at the target.
						else
							(event claimed: FALSE)
						)
					)
					((Said 'get/spear')
						(HighPrint 73 12)
						;There are no useable spears near you.
					)
					((Said 'hop/bush')
						(HighPrint 73 13)
						;These bushes are full of thorns. You decide not to risk it.
					)
					((Said 'search,look/body,bandit,bruno,brutus')
						(if (Btst fBeatBrutus)
							(HighPrint 73 14)
							;Some marauding beast must have taken the brigand's body away.
						else
							(HighPrint 73 15)
							;He isn't here.
						)
					)
					((Said 'rest[/!*]')
						(if (> brunoTimer 50)
							(-= brunoTimer 50)
						)
						(event claimed: FALSE)
					)
					((Said 'cast>')
						(= spell (SaidSpell event))
						(if (CastSpell spell)
							(switch spell
								(DETMAGIC
									(HighPrint 73 16)
									;You detect no magic here.
								)
								(DAZZLE
									(if (CastDazz)
										(if
										(or (Btst fBeatBrutus) (== (self script?) nobodyHere))
											(HighPrint 73 17)
											;You waste a spell.
										else
											(= local12 1)
										)
									)
								)
								(FLAMEDART
									(if (or (Btst fBeatBrutus) (== (self script?) nobodyHere))
										(CastDart 0)
									else
										(CastDart brutus)
									)
								)
								(CALM
									(HighPrint 73 18)
									;You've wasted a spell.
									(if (and (cast contains: brutus) (not (Btst fBeatBrutus)))
										(HighPrint 73 19)
										;The brigand is too angry to calm.
									)
								)
								(OPEN
									(HighPrint 73 18)
									;You've wasted a spell.
									(HighPrint 73 20)
									;There's nothing here to open.
								)
								(ZAP
									(= zapPower (+ 5 (/ [egoStats ZAP] 10)))
									(if (or (ego has: iDagger) (ego has: iSword))
										(HighPrint 73 21)
										;Your weapon is now magically charged.
									else
										(HighPrint 73 22)
										;You don't seem to have a weapon to charge.
									)
								)
								(FETCH
									(if (or (Btst fBeatBrutus) (== (self script?) nobodyHere))
										(HighPrint 73 23)
										;You waste a spell. Fetch is only good for fetching small, visible objects.
									else
										(HighPrint 73 24)
										;Just wait. The nice brigand will throw you his knife.
									)
								)
								(else
									(HighPrint 73 25)
									;That spell is not useful here.
								)
							)
						)
					)
					((Said 'throw/dagger,dagger')
						(= theBrutus 0)
						(if (and (cast contains: brutus) (not (Btst fBeatBrutus)))
							(Face ego brutus)
							(= theBrutus brutus)
						)
						(ThrowKnife theBrutus)
					)
					((Said 'throw/boulder')
						(= theBrutus 0)
						(if (and (cast contains: brutus) (not (Btst fBeatBrutus)))
							(Face ego brutus)
							(= theBrutus brutus)
						)
						(ThrowRock theBrutus)
					)
					((Said 'climb,climb[/wall]')
						(if Night
							(if (< (ego y?) 135)
								(if (TrySkill CLIMB tryClimbIntoTown 0)
									(HighPrint 73 26)
									;After making sure nobody is watching, you climb over the town's wall.
									(curRoom newRoom: 300)
								else
									(HighPrint 73 27)
									;Climbing this wall is too difficult for your level of skill.  Keep practicing.
								)
							else
								(HighPrint 73 28)
								;You're not in a good spot for climbing the wall.
							)
						else
							(HighPrint 73 29)
							;You would have trouble convincing people you are a Hero if you climbed the wall into town during the day.
						)
					)
					((Said 'look/east,south,west,forest,forest')
						(HighPrint 73 30)
						;The forest is very overgrown near here.
						)
					((Said 'look[<at,around][/!*,range,clearing,area,hamlet,wall,north,building]')
						(HighPrint 73 31)
						;The wall and buildings of Spielburg can be seen over the heavy brush.  An old target leans against the town wall.
					)
				)
			)
		)
		(super handleEvent: event)
	)
)

(instance brigsMeet of Script
	(method (changeState newState)
		(if client
			(switch (= state newState)
				(0
					(Bset fSpiedOnThieves)
					(brunoTalk caller: self)
					(brutusTalk caller: self)
					(bruno init:)
					(brutus init:)
					(brunoTalk tWindow: brunoWin actor: bruno init:)
					(brutusTalk tWindow: brutusWin actor: brutus init:)
					(mouseDownHandler add: bruno brutus)
					(keyDownHandler add: bruno brutus)
					(= seconds 4)
				)
				(1
					(Say brunoTalk 73 33)
					;"So what's this about, anyway?"
				)
				(2
					(Say brutusTalk 73 34)
					;"Her Nibs is starting to get suspicious about us."
				)
				(3
					(Say brunoTalk 73 35)
					;"What's the bee in her bonnet?"
				)
				(4
					(Say brutusTalk 73 36)
					;"Seems the "hero" wandering around here has her leery.  She thinks he's going to go for the gold on her head."
				)
				(5
					(Say brunoTalk 73 37)
					;"What's it ta do with us?"
				)
				(6
					(Say brutusTalk 73 38 73 39)
					;"She's been asking too many questions 'bout us."
					;"And the laughing jackass' eyeing me.  I had to sneak out."
				)
				(7
					(Say brunoTalk 73 40)
					;"Then we got to avoid the ambush and use the back for a bit, 'til the heat is off."
				)
				(8
					(Say brutusTalk 73 41)
					;"Maybe we should just make our move now."
				)
				(9
					(Say brunoTalk 73 42 73 43 73 44)
					;"Naw, let's wait for the creep to go first."
					;"While she's busy with him, we take over."
					;"She'll take him out easy, then we take her out."
				)
				(10
					(Say brutusTalk 73 45)
					;"Where's the back door, then?"
				)
				(11
					(Say brunoTalk 73 46)
					;"Where the bouncer hops around.  Ya got your key still?"
				)
				(12
					(Say brutusTalk 73 47)
					;"Yeah."
				)
				(13
					(Say brunoTalk 73 48)
					;"Don't lose it.  I got the only other one.  Yull haveta search the rock for the keyhole.
					;It's hidden good.  And remember the 'word'."
				)
				(14
					(Say brutusTalk 73 49)
					;"What Word?"
				)
				(15
					(Say brunoTalk 73 50)
					;"The 'word' what lets ya in so that Fred goes away."
				)
				(16
					(Say brutusTalk 73 51 73 52)
					;"Oh yeah, sure."
					;"What is it?"
				)
				(17
					(Say brunoTalk 73 53)
					;"Hiden Goseke.  Ya better learn it."
				)
				(18
					(Say brutusTalk 73 54)
					;"You think I'm a dummy or somethin'?"
				)
				(19
					(Say brunoTalk 73 55 73 56)
					;"Say the 'word' before ya open the door or ya might make Fred mad."
					;"Ya don't want Fred ta get mad."
				)
				(20
					(Say brutusTalk 73 57)
					;"Hey, no problem.  What's the 'word' again?"
				)
				(21
					(Say brunoTalk 73 58)
					;"Hiden Goseke."
				)
				(22
					(Say brutusTalk 73 59)
					;"Hiden Goseke.  Got it."
				)
				(23
					(Say brunoTalk 73 60)
					;"I gotta get back before the Chief misses me.  Be back in a bit."
				)
				(24
					(Say brutusTalk 73 61)
					;"Hiden Goseke.  See ya."
					(SolvePuzzle f73OverhearBruno 12)
				)
				(25
					(bruno
						illegalBits: 0
						ignoreActors:
						setLoop: -1
						setCycle: Walk
						setMotion: MoveTo 170 226 self
					)
					(User canControl: FALSE)
				)
				(26
					(bruno dispose:)
					(User canControl: TRUE)
					(= brunoTimer 300)
					(client setScript: brutusWaits)
				)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look/man,man,thief,bandit')
				(HighPrint 73 32)
				;You see a couple of hard-looking characters talking. 
				; One looks like a thief; the other appears to be a fighter of some sort.
				)
			((Said 'ask,chat,throw,cast')
				(brunoTalk caller: 0 endTalk:)
				(brutusTalk caller: 0 endTalk:)
				(bruno setMotion: 0)
				(client setScript: egoLoses)
			)
		)
	)
)

(instance egoLoses of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(bruno
					setLoop: (if (> (ego x?) 160) 5 else 6)
					cel: 0
					cycleSpeed: 2
					setCycle: CycleTo 4 1 self
				)
			)
			(1
				(knife1
					illegalBits: 0
					setLoop: 7
					ignoreActors:
					ignoreHorizon:
					xStep: 6
					yStep: 7
					init:
					setPri: (- (ego priority?) 1)
					setCycle: Forward
					posn: (+ (bruno x?) (* local5 41)) (- (bruno y?) 24)
					setMotion: MoveTo (ego x?) (- (ego y?) 22)
				)
				(bruno setCycle: CycleTo 5 1 self)
			)
			(2
				(bruno setCycle: EndLoop)
				(knife2
					illegalBits: 0
					setLoop: 8
					ignoreActors:
					ignoreHorizon:
					xStep: 6
					yStep: 7
					setPri: (- (ego priority?) 1)
					init:
					setCycle: Forward
					posn: (+ (bruno x?) (* 41 local5)) (- (bruno y?) 27)
					setMotion: MoveTo (ego x?) (- (ego y?) 25) self
				)
				(ego setLoop: 1)
			)
			(3
				(knife1 dispose:)
				(knife2 dispose:)
				(ego
					view: vEgoDefeatedMagic
					setLoop: 3
					cycleSpeed: 1
					setMotion: 0
					setCycle: EndLoop self
				)
			)
			(4 (= seconds 4))
			(5
				(EgoDead 73 62
					#icon vEgoDefeatedMagic 2 5
					#title {Keep your mouth shut.})
					;Done in by poisoned daggers and desperate desperadoes!  Try to avoid annoying that deadly duo next time.
			)
		)
	)
)

(instance brutusWaits of Script
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds brunoTimer))
			(1
				(if (not (Btst fBeatBrutus))
					(brutus
						illegalBits: 0
						ignoreActors:
						setLoop: -1
						loop: 1
						setCycle: Walk
						setMotion: MoveTo 170 224 self
					)
				)
			)
			(2 (brutus dispose:))
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'ask,chat')
				(brutus setMotion: 0)
				(client setScript: brutusThrows)
			)
			(
				(and
					(!= (brutus script?) brutusThrows)
					(not (Btst fBeatBrutus))
					(Said 'throw,cast')
				)
				(brutus setMotion: 0)
				(brutus setScript: brutusThrows)
				(event claimed: FALSE)
			)
		)
	)
)

(instance brutusDies of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if daggerBeingThrown
					(knife1 dispose:)
				)
				(NormalEgo)
				(ego setScript: 0)
				(HandsOff)
				(if (== prevRoomNum vBrigand)
					(ego posn: 152 128)
					(brutus view: vBrigandDefeated loop: 0 init:)
				else
					(brutus view: vBrutus setLoop: 7)
				)
				(brutus
					posn: 139 114
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(1
				(HandsOn)
				(= brutusHostile FALSE)
				(brutus stopUpd:)
				(dags add:)
				(client setScript: nobodyHere)
			)
		)
	)
)

(instance brutusLives of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(brutus
					init:
					setCycle: Walk
					setMotion: MoveTo (ego x?) (ego y?)
				)
				(brutusTalk actor: 0)
				(HighPrint 73 63)
				;"Hey, watchu doin' here? Spyin', I'll wager!"
				(= cycles 11)
			)
			(1 (curRoom newRoom: vBrigand))
		)
	)
)

(instance brutusThrows of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(brutus
					setLoop: (if (> (ego x?) 160) 5 else 6)
					cel: 0
					cycleSpeed: 2
					setCycle: CycleTo 4 1 self
				)
			)
			(1
				(brutus setCycle: CycleTo 5 1 self)
			)
			(2
				(if local12
					(self cue:)
				else
					(brutus setCycle: EndLoop self)
					(= brutusHostile TRUE)
				)
			)
			(3
				(if local12
					(self cue:)
				else
					(= daggerBeingThrown TRUE)
					(knife1
						illegalBits: 0
						setLoop: 7
						ignoreActors:
						ignoreHorizon:
						xStep: 6
						yStep: 7
						init:
						setPri: (- (ego priority?) 1)
						setCycle: Forward
						posn: (+ (brutus x?) (* local5 32)) (- (brutus y?) 20)
						setMotion: MoveTo (ego x?) (- (ego y?) 22) self
					)
				)
			)
			(4
				(if daggerBeingThrown
					(= daggerBeingThrown FALSE)
					(knife1 dispose:)
					(if (not (TakeDamage (- 10 armorProtection)))
						(self changeState: 7)
					)
					(ego
						view: vEgoDaggerDefeated
						setLoop: (if (== local5 1) 3 else 2)
						cycleSpeed: 1
						setMotion: 0
						setCycle: EndLoop
					)
					(= cycles 12)
				else
					(self cue:)
				)
			)
			(5
				(NormalEgo)
				(Face ego brutus)
				(= brutusHostile FALSE)
				(if local12
					(= seconds 12)
					(= local12 0)
				else
					(= seconds 3)
				)
			)
			(6 (self changeState: 0))
			(7
				(ego
					view: vEgoDefeatedMagic
					setLoop: 2
					setCel: -1
					cel: 0
					cycleSpeed: 1
					setMotion: 0
					setCycle: EndLoop self
				)
			)
			(8 (= seconds 3))
			(9
				(EgoDead 73 64
					#icon vEgoDefeatedMagic 2 5
					#title {Daggered to death.})
					;Done in by daggers and a desperate desperado!  Try to avoid annoying that deadly duo next time.
			)
		)
	)
)

(instance nobodyHere of Script
	(method (changeState newState &tmp newProp temp1 temp2 temp3)
		(switch (= state newState)
			(1
				(HandsOff)
				(ego illegalBits: 0 ignoreActors:)
				(= oldEgoY (ego y?))
				(cond 
					((< (ego x?) 129) (ego setMotion: MoveTo 134 (ego y?) self))
					((> (ego x?) 180) (ego setMotion: MoveTo 173 (ego y?) self))
					((< (ego y?) 110) (ego setMotion: MoveTo (ego x?) 116 self))
					(else (self cue:))
				)
			)
			(2
				(ego setMotion: MoveTo 186 109 self)
			)
			(3
				(dags eachElementDo: #dispose 84 release:)
				(= seconds 2)
			)
			(4
				(dags add:)
				(ego
					setMotion: MoveTo 156 (if (> oldEgoY 115) oldEgoY else 115) self
				)
			)
			(5
				(NormalEgo)
				(ego loop: 3)
				(HandsOn)
			)
			(6
				(HandsOff)
				(ego
					view: vEgoThrowing
					setLoop: 2
					cel: 0
					cycleSpeed: 2
					setCycle: CycleTo 3 1 self
				)
			)
			(7
				(ego setCycle: EndLoop)
				(TrySkill THROW 0 20)
				(= temp3 (/ (- 120 [egoStats THROW]) 10))
				(= temp1 (- (Random 0 temp3) (/ temp3 2)))
				(= temp2 (- (Random 0 temp3) (/ temp3 2)))
				(knife1
					illegalBits: 0
					setLoop: 7
					ignoreActors:
					ignoreHorizon:
					xStep: 6
					yStep: 7
					init:
					setPri: (- (ego priority?) 1)
					setCycle: Forward
					posn: (+ (ego x?) 30) (- (ego y?) 24)
					setMotion:
						MoveTo
						(+ (target x?) temp1)
						(+ (- (target y?) 22) temp2)
						self
				)
			)
			(8
				(if (< daggersOnTarget local8)
					((= newProp (Prop new:))
						init:
						view: vBruno
						loop: 7
						cel: (Random 0 6)
						posn: (knife1 x?) (knife1 y?)
						stopUpd:
					)
					(dags add: newProp)
				)
				(knife1 dispose:)
				(NormalEgo)
				(ego loop: 3)
				(HandsOn)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'throw/dagger,dagger')
				(cond 
					((or (== prevRoomNum 72) (== prevRoomNum 74))
						(HighPrint 73 65)
						;There are too many bushes between you and the target.
						)
					((< (ego y?) 135)
						(HighPrint 73 66)
						;You're too close to the target for it to be a challange.
						)
					((ego has: iDagger)
						(if (!= daggerRoom curRoomNum)
							(= daggerRoom curRoomNum)
							(= missedDaggers (= hitDaggers 0))
						)
						(-- [invNum iDagger])
						(++ daggersOnTarget)
						(self changeState: 6)
					)
					(else
						(HighPrint 73 67)
						;You have no daggers to throw.
					)
				)
			)
			((and (Said 'get/dagger') (RetrieveDaggers TRUE))
				(self changeState: 1)
			)
		)
	)
)

(instance egoSearch of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					ignoreActors: TRUE
					illegalBits: 0
					setMotion: MoveTo 145 120 self
				)
			)
			(1
				(Face ego brutus)
				(ego
					loop: (mod (+ (ego loop?) 4) 2)
					view: vEgoThrowing
					setCycle: EndLoop self
				)
			)
			(2
				(RetrieveDaggers 0)
				(if (Btst fGotBrutusKey)
					(HighPrint 73 68)
					;You find nothing else on the brigand's body.
				else
					(HighPrint 73 69)
					;You find a single key on the brigand's body, and put it away.
					(Bset fGotBrutusKey)
					(ego get: iBrassKey)
				)
				(NormalEgo)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)
