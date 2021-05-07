;;; Sierra Script 1.0 - (do not remove this comment)
(script# 34)
(include sci.sh)
(use Main)
(use Intrface)
(use DCIcon)
(use RFeature)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	Room34 0
)

(local
	local0
	local1
	local2
	local3
)
(instance Room34 of Rm
	(properties
		picture 34
	)
	
	(method (init)
		(super init:)
		(= west 33)
		(Load rsVIEW 22)
		(LoadMany 132 9 47 74 75)
		(addToPics
			add:
				chair1
				chair2
				chair3
				chair4
				chair5
				chair6
				chair7
				chair8
				chair9
				chair10
				chair11
				chair12
				coffee
				chandelier
				flowers
			eachElementDo: #init
			doit:
		)
		(self
			setRegions: 213
			setFeatures:
				Table
				chair7
				chair8
				chair9
				chair10
				chair11
				chair12
				BigTable
				chair1
				chair2
				chair3
				chair4
				chair5
				chair6
				coffee
				chandelier
				Hutch
				Mirror
		)
		(if howFast
			(gas setPri: 9 ignoreActors: 1 setCycle: Fwd init:)
			(fire loop: (/ currentAct 2) setCycle: Fwd init:)
		else
			(gas setPri: 9 ignoreActors: 1 init: stopUpd:)
			(fire loop: (/ currentAct 2) init: stopUpd:)
		)
		(chute
			setLoop: 2
			yStep: 5
			illegalBits: 0
			setPri: 2
			ignoreActors: 1
			init:
			stopUpd:
			setScript: chuteActions
		)
		(if
			(and
				(<= (Random 1 100) 35)
				(> currentAct 0)
				(< currentAct 7)
			)
			(Shadow illegalBits: 0 posn: 13 82 setPri: 2 init:)
			(Shadow setScript: shadowWalk)
		)
		(switch currentAct
			(1
				(if (== global154 3)
					(if (== [global368 0] 1)
						(= global154 4)
					else
						(if (== global154 3) (User canInput: 0))
						(= local2 1)
						(self setRegions: 237)
					)
				)
			)
			(4
				(= local2 1)
				(self setRegions: 403)
			)
			(3
				(if (and (== gameMinutes 3) (== [global368 2] 0))
					(= [global368 2] 1800)
				)
				(if (> [global368 2] 1)
					(= local2 1)
					(self setRegions: 383)
				)
			)
		)
		(if (!= prevRoomNum 50)
			(if (== prevRoomNum 33)
				(ego posn: 14 150)
			else
				(ego posn: 265 120)
			)
			(ego illegalBits: -32768 view: 0 init:)
		else
			(ego
				view: 0
				illegalBits: -32768
				setPri: -1
				posn: 68 167
				init:
			)
			(= local3 1)
		)
	)
	
	(method (doit)
		(if local3 (= local3 0) (Print 34 0))
		(if (FirstEntry) (Print 34 1))
		(if (& (ego onControl: 1) $0004) (curRoom newRoom: 35))
		(if (not local1)
			(if (and (< (ego x?) 51) (> (ego y?) 126))
				(ego setPri: 10)
			else
				(ego setPri: -1)
			)
		)
		(cond 
			((< (ego x?) 30) (= vertAngle 0))
			((< (ego x?) 140) (= vertAngle 163))
			(else (= vertAngle 137))
		)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp temp0)
		(asm
			pushi    #claimed
			pushi    0
			lap      event
			send     4
			bnt      code_0363
			ldi      1
			ret     
code_0363:
			pushi    #type
			pushi    0
			lap      event
			send     4
			push    
			ldi      128
			eq?     
			bnt      code_05bf
			lsg      currentAct
			ldi      1
			ne?     
			bt       code_0397
			lsg      global154
			ldi      3
			ne?     
			bt       code_0397
			pushi    1
			lofsa    'ask[/c]/attorney<about>'
			push    
			callk    Said,  2
			not     
			bnt      code_0397
			pushi    1
			lofsa    'ask[/attorney]/c<about>'
			push    
			callk    Said,  2
			not     
code_0397:
			sat      temp0
			lag      global208
			bnt      code_03dc
			lat      temp0
			bnt      code_03dc
			pushi    1
			lofsa    'ask,tell,hold,deliver,examine,get,kill,kiss,embrace,flirt>'
			push    
			callk    Said,  2
			bnt      code_03dc
			pushi    1
			pushi    990
			callk    DisposeScript,  2
			pushi    #setScript
			pushi    1
			pushi    2
			pushi    243
			pushi    0
			callk    ScriptID,  4
			push    
			self     6
			pushi    #handleEvent
			pushi    1
			lsp      event
			pushi    #script
			pushi    0
			self     4
			send     6
			pushi    #claimed
			pushi    0
			lap      event
			send     4
			bnt      code_03dc
			ldi      1
			ret     
code_03dc:
			pushi    1
			lofsa    '/panel,(door<hidden)>'
			push    
			callk    Said,  2
			bnt      code_045a
			pushi    1
			lofsa    'examine'
			push    
			callk    Said,  2
			bnt      code_0411
			lsg      global175
			ldi      2
			and     
			bnt      code_0405
			pushi    2
			pushi    34
			pushi    2
			calle    Print,  4
			jmp      code_05bf
code_0405:
			pushi    2
			pushi    34
			pushi    3
			calle    Print,  4
			jmp      code_05bf
code_0411:
			lsg      global175
			ldi      2
			and     
			bnt      code_05bf
			pushi    1
			lofsa    'open,move'
			push    
			callk    Said,  2
			bnt      code_05bf
			lal      local2
			not     
			bnt      code_044e
			pushi    #onControl
			pushi    1
			pushi    1
			lag      ego
			send     6
			push    
			ldi      8
			and     
			bnt      code_0447
			pushi    #newRoom
			pushi    1
			pushi    50
			lag      curRoom
			send     6
			jmp      code_05bf
code_0447:
			pushi    0
			callb    NotClose,  0
			jmp      code_05bf
code_044e:
			pushi    2
			pushi    34
			pushi    4
			calle    Print,  4
			jmp      code_05bf
code_045a:
			pushi    1
			lofsa    'examine>'
			push    
			callk    Said,  2
			bnt      code_055b
			pushi    1
			lofsa    '[<around,at][/room]'
			push    
			callk    Said,  2
			bnt      code_047b
			pushi    2
			pushi    34
			pushi    1
			calle    Print,  4
			jmp      code_05bf
code_047b:
			pushi    1
			lofsa    'examine/panel[<hidden]'
			push    
			callk    Said,  2
			bnt      code_04a5
			lsg      global175
			ldi      2
			and     
			bnt      code_0499
			pushi    2
			pushi    34
			pushi    2
			calle    Print,  4
			jmp      code_05bf
code_0499:
			pushi    2
			pushi    34
			pushi    3
			calle    Print,  4
			jmp      code_05bf
code_04a5:
			pushi    1
			lofsa    '/wall,door'
			push    
			callk    Said,  2
			bnt      code_04bc
			pushi    2
			pushi    34
			pushi    5
			calle    Print,  4
			jmp      code_05bf
code_04bc:
			pushi    1
			lofsa    '<below/nightstand'
			push    
			callk    Said,  2
			bnt      code_04d3
			pushi    2
			pushi    34
			pushi    6
			calle    Print,  4
			jmp      code_05bf
code_04d3:
			pushi    1
			lofsa    '/nightstand'
			push    
			callk    Said,  2
			bnt      code_04ea
			pushi    2
			pushi    34
			pushi    7
			calle    Print,  4
			jmp      code_05bf
code_04ea:
			pushi    1
			lofsa    'examine/eye>'
			push    
			callk    Said,  2
			bnt      code_0500
			pushi    1
			lofsa    'examine/fellow'
			push    
			callk    Said,  2
			bt       code_0516
code_0500:
			pushi    1
			lofsa    'examine/eye[<fellow,painting]'
			push    
			callk    Said,  2
			bt       code_0516
			pushi    1
			lofsa    'examine/eye/fellow'
			push    
			callk    Said,  2
			bnt      code_0522
code_0516:
			pushi    2
			pushi    34
			pushi    8
			calle    Print,  4
			jmp      code_05bf
code_0522:
			pushi    1
			lofsa    '<behind,below/painting'
			push    
			callk    Said,  2
			bnt      code_0539
			pushi    2
			pushi    34
			pushi    9
			calle    Print,  4
			jmp      code_05bf
code_0539:
			pushi    1
			lofsa    '/painting'
			push    
			callk    Said,  2
			bt       code_054f
			pushi    1
			lofsa    '/fellow/painting'
			push    
			callk    Said,  2
			bnt      code_05bf
code_054f:
			pushi    2
			pushi    34
			pushi    10
			calle    Print,  4
			jmp      code_05bf
code_055b:
			pushi    1
			lofsa    'move,get/painting'
			push    
			callk    Said,  2
			bnt      code_0572
			pushi    2
			pushi    34
			pushi    11
			calle    Print,  4
			jmp      code_05bf
code_0572:
			pushi    1
			lofsa    'get>'
			push    
			callk    Said,  2
			bnt      code_05bf
			pushi    1
			lofsa    '/mirror'
			push    
			callk    Said,  2
			bnt      code_0594
			pushi    2
			pushi    34
			pushi    12
			calle    Print,  4
			jmp      code_05bf
code_0594:
			pushi    1
			lofsa    '/coffee'
			push    
			callk    Said,  2
			bnt      code_05ab
			pushi    2
			pushi    34
			pushi    13
			calle    Print,  4
			jmp      code_05bf
code_05ab:
			pushi    1
			lofsa    '/fire,log'
			push    
			callk    Said,  2
			bnt      code_05bf
			pushi    2
			pushi    34
			pushi    14
			calle    Print,  4
code_05bf:
			ret     
		)
	)
	
	(method (newRoom n)
		(super newRoom: n)
	)
)

(instance chuteActions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (cls))
			(1
				(User canControl: 0)
				(ego illegalBits: 0 setMotion: MoveTo 33 127 self)
			)
			(2
				(ego view: 22 loop: 0 cel: 0 setCycle: End)
				(chute setMotion: MoveTo 19 167 self)
				(myMusic number: 74 loop: 1 play:)
			)
			(3 (= local0 1) (Print 34 20))
			(4
				(chute setMotion: MoveTo 23 121)
				(= local0 0)
				(myMusic number: 75 loop: 1 play:)
				(ego setCycle: Beg self)
			)
			(5
				(ego view: 0 loop: 1 setCycle: Walk illegalBits: -32768)
				(User canControl: 1)
			)
			(6
				(myMusic number: 9 loop: 1 play:)
				(ego
					setLoop: 2
					cel: 0
					setMotion: MoveTo 27 128
					setCycle: End self
				)
			)
			(7
				(Print 34 21 #dispose)
				(= seconds 4)
			)
			(8
				(cls)
				(= global172 100)
				(ShakeScreen 10 5)
				(myMusic number: 47 loop: 1 play:)
				(= cycles 21)
			)
			(9
				(= local1 1)
				(myMusic number: 57 loop: 1 play: self)
				(ego
					view: 38
					posn: 20 200
					setCycle: Walk
					setPri: 9
					illegalBits: 0
					setMotion: MoveTo 25 -1 self
				)
			)
			(10
				(= cIcon myIcon)
				(= deathLoop 0)
			)
			(11
				(= cyclingIcon 1)
				(EgoDead 34 22)
			)
		)
	)
	
	(method (handleEvent event)
		(if (== (event type?) evSAID)
			(cond 
				((event claimed?))
				((Said 'examine<(down,up)') (if local0 (Print 34 15) else (event claimed: 0)))
				((Said 'examine/chute') (if local0 (Print 34 15) else (Print 34 16)))
				((Said 'open/door[<chute]')
					(cond 
						(local0 (Print 34 17))
						((ego inRect: 5 126 40 135) (= state 0) (= cycles 1))
						(else (NotClose))
					)
				)
				((Said 'hop,crawl,go,enter/chute')
					(if local0
						(HandsOff)
						(= state 5)
						(= cycles 1)
					else
						(Print 34 18)
					)
				)
				((or (Said 'stand') (Said 'close/door,chute')) (if local0 (= cycles 1) else (Print 34 19)))
			)
		)
	)
)

(instance shadowWalk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 8))
			(1
				(Shadow setMotion: MoveTo 295 82 self)
			)
			(2
				(Shadow dispose:)
				(client setScript: 0)
			)
		)
	)
)

(instance chair1 of RPicView
	(properties
		y 113
		x 106
		view 134
		loop 5
		cel 4
		priority 7
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(ParseName {chair})
		)
	)
)

(instance chair2 of RPicView
	(properties
		y 113
		x 136
		view 134
		loop 5
		cel 4
		priority 7
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(ParseName {chair})
		)
	)
)

(instance chair3 of RPicView
	(properties
		y 113
		x 164
		view 134
		loop 5
		cel 4
		priority 7
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(ParseName {chair})
		)
	)
)

(instance chair4 of RPicView
	(properties
		y 90
		x 205
		view 134
		loop 6
		cel 1
		priority 5
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(ParseName {chair})
		)
	)
)

(instance chair5 of RPicView
	(properties
		y 90
		x 50
		view 134
		loop 6
		priority 5
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(ParseName {chair})
		)
	)
)

(instance chair6 of RPicView
	(properties
		y 112
		x 188
		view 134
		loop 5
		cel 2
		priority 7
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(ParseName {chair})
		)
	)
)

(instance chair7 of RPicView
	(properties
		y 125
		x 220
		view 134
		loop 5
		priority 8
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(ParseName {chair})
		)
	)
)

(instance chair8 of RPicView
	(properties
		y 131
		x 87
		view 134
		loop 5
		cel 7
		priority 8
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(ParseName {chair})
		)
	)
)

(instance chair9 of RPicView
	(properties
		y 130
		x 121
		view 134
		loop 5
		cel 9
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(ParseName {chair})
		)
	)
)

(instance chair10 of RPicView
	(properties
		y 130
		x 153
		view 134
		loop 5
		cel 11
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(ParseName {chair})
		)
	)
)

(instance chair11 of RPicView
	(properties
		y 130
		x 181
		view 134
		loop 5
		cel 11
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(ParseName {chair})
		)
	)
)

(instance chair12 of RPicView
	(properties
		y 130
		x 212
		view 134
		loop 5
		cel 12
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(ParseName {chair})
		)
	)
)

(instance coffee of RPicView
	(properties
		y 138
		x 301
		view 134
		priority 12
	)
	
	(method (handleEvent event)
		(cond 
			(
				(or
					(Said 'open/coffee,urn[<urn]')
					(Said 'examine<in/coffee,urn')
					(Said 'pour,get,drink/cup,coffee')
				)
				(cond 
					((== currentAct 4) (Print 34 23))
					((ego inRect: 259 139 320 200) (Print 34 13))
					(else (NotClose))
				)
			)
			((Said 'get/urn') (Print 34 24))
			(
			(or (MousedOn self event 3) (Said 'examine/urn')) (event claimed: 1) (Print 34 25))
		)
	)
)

(instance chandelier of RPicView
	(properties
		y 42
		x 144
		view 134
		cel 3
		priority 9
	)
	
	(method (handleEvent event)
		(if
		(or (MousedOn self event 3) (Said 'examine/chandelier'))
			(event claimed: 1)
			(Print 34 26)
		)
	)
)

(instance flowers of PV
	(properties
		y 55
		x 129
		view 134
		cel 2
		priority 5
	)
)

(instance fire of Prop
	(properties
		y 85
		x 131
		view 232
		cel 1
		priority 5
	)
	
	(method (handleEvent event)
		(if
		(or (MousedOn self event 3) (Said 'examine/log'))
			(event claimed: 1)
			(ParseName {fire})
		)
	)
)

(instance gas of Prop
	(properties
		y 82
		x 24
		view 134
		loop 3
		cel 1
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(event claimed: 1)
			(ParseName {lamp})
		)
	)
)

(instance chute of Act
	(properties
		y 121
		x 23
		view 134
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if
		(or (MousedOn self event 3) (Said 'examine/door'))
			(Print 34 5)
			(event claimed: 1)
		)
	)
)

(instance Shadow of Act
	(properties
		view 110
	)
)

(instance Hutch of RFeature
	(properties
		nsTop 46
		nsLeft 222
		nsBottom 106
		nsRight 247
	)
	
	(method (handleEvent event)
		(cond 
			(
			(or (Said 'examine<in/armoire') (Said 'open/armoire')) (Print 34 27))
			(
			(or (MousedOn self event 3) (Said 'examine/armoire')) (Print 34 28) (event claimed: 1))
		)
	)
)

(instance BigTable of RFeature
	(properties
		nsTop 95
		nsLeft 99
		nsBottom 107
		nsRight 199
	)
	
	(method (handleEvent event)
		(if
			(or
				(MousedOn self event 3)
				(Said 'examine/nightstand<dining')
			)
			(Print 34 29)
			(event claimed: 1)
		)
	)
)

(instance Table of RFeature
	(properties
		nsTop 133
		nsLeft 284
		nsBottom 168
		nsRight 319
	)
	
	(method (handleEvent event)
		(if
		(or (MousedOn self event 3) (Said '/nightstand<little'))
			(Print 34 30)
			(event claimed: 1)
		)
	)
)

(instance Mirror of RFeature
	(properties
		nsTop 16
		nsLeft 107
		nsBottom 48
		nsRight 141
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'examine<behind,below/mirror') (Print 34 31))
			(
				(or
					(Said 'examine<in/mirror')
					(Said 'examine[<at]/reflection')
				)
				(if (< (ego distanceTo: fire) 80)
					(= theTalker 12)
					(Say 0 34 32)
				else
					(NotClose)
				)
			)
			(
			(or (MousedOn self event 3) (Said 'examine/mirror')) (Print 34 33) (event claimed: 1))
		)
	)
)

(instance myMusic of Sound
	(properties)
)

(instance myIcon of DCIcon
	(properties
		view 653
		cycleSpeed 16
	)
)
