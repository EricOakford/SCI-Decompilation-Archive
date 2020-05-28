;;; Sierra Script 1.0 - (do not remove this comment)
(script# BUTTE) ;703
(include game.sh)
(use Main)
(use rm395)
(use Sq4Narrator)
(use Sq4Feature)
(use StopWalk)
(use Motion)
(use Game)
(use User)
(use System)

(public
	butte 0
	butteFallScript 1
	policeLand 2
	proc703_3 3
	cop 4
	shootEgoScript 5
	theRoom 6
)

(local
	[theOldPoliceRoom 13] = [300 305 315 310 315 305 300 299 306 299 300 310 320]
	[theOldPoliceRoom_2 13] = [300 300 310 320 310 300 299 306 299 300 305 315 310]
	local26 =  1
	local27 =  1
	local28
	local29
	local30
	local31
	local32
	theSpeed
)
(procedure (proc703_3)
	(asm
		pushi    3
		pushi    1
		pushi    1
		pushi    39
		pushi    0
		lag      music
		send     22
		pushi    2
		pushi    1
		pushi    300
		pushi    1
		pushi    1
		pushi    #loop
		pushi    0
		lag      ego
		send     4
		push    
		dup     
		ldi      0
		eq?     
		bnt      code_03d8
		pushi    #x
		pushi    0
		lag      ego
		send     4
		push    
		ldi      10
		add     
		jmp      code_03f0
code_03d8:
		dup     
		ldi      1
		eq?     
		bnt      code_03ea
		pushi    #x
		pushi    0
		lag      ego
		send     4
		push    
		ldi      10
		sub     
		jmp      code_03f0
code_03ea:
		pushi    #x
		pushi    0
		lag      ego
		send     4
code_03f0:
		toss    
		push    
		pushi    155
		pushi    0
		pushi    161
		pushi    1
		pushi    0
		pushi    315
		pushi    2
		pushi    3
		pushi    20
		pushi    63
		pushi    0
		pushi    15
		pushi    1
		pushi    0
		pushi    290
		pushi    4
		class    MoveTo
		push    
		pushi    #x
		pushi    0
		lag      ego
		send     4
		push    
		pushi    250
		pushSelf
		lag      ego
		send     52
		jmp      code_0452
		dup     
		ldi      1
		eq?     
		bnt      code_042f
		ldi      3
		aTop     28
		; WARNING: Can't determine property name for index 28
		jmp      code_0452
code_042f:
		dup     
		ldi      2
		eq?     
		bnt      code_0448
		pushi    #fade
		pushi    4
		pushi    0
		pushi    1
		pushi    2
		pushi    1
		lag      music
		send     12
		ldi      1
		aTop     28
		; WARNING: Can't determine property name for index 28
		jmp      code_0452
code_0448:
		dup     
		ldi      3
		eq?     
		bnt      code_0452
		pushi    0
		callb    EgoDead,  0
code_0452:
		toss    
		ret     
	)
)

(procedure (localproc_087f &tmp copLoop)
	(= copLoop (cop loop?))
	(DirLoop
		cop
		(GetAngle (cop x?) (cop y?) (ego x?) (ego y?))
	)
	(if (!= copLoop (cop loop?)) (cop forceUpd:))
	(cop setMotion: 0)
)

(procedure (localproc_08ff)
	(return
		(if
			(and
				(< (ego x?) 260)
				(> (ego x?) 60)
				(< (ego y?) 160)
			)
			(> (ego y?) 80)
		else
			0
		)
	)
)

(procedure (localproc_0c4d param1 param2 param3 param4 &tmp temp0 temp1 temp2)
	(globalSound number: 105 vol: 127 loop: 1 play:)
	(if (< param1 param3)
		(= temp0 (- param1 1))
		(= temp1 (+ param3 1))
	else
		(= temp0 (- param3 1))
		(= temp1 (+ param1 1))
	)
	(= temp2
		(Graph
			GSaveBits
			temp0
			(- param2 1)
			temp1
			(+ param4 1)
			1
		)
	)
	(Graph
		GDrawLine
		param1
		param2
		param3
		param4
		myTextColor13
		-1
		-1
	)
	(Graph
		GReAnimate
		temp0
		(- param2 1)
		temp1
		(+ param4 1)
	)
	(Wait 1)
	(Wait 4)
	(Graph GRestoreBits temp2)
	(Graph
		GReAnimate
		temp0
		(- param2 1)
		temp1
		(+ param4 1)
		1
	)
)

(class rssScript of Script
	(properties)
	
	(method (doit &tmp temp0 temp1)
		(super doit:)
		(if local30
			(if
				(or
					(== (= temp0 ((User curEvent?) type?)) 1)
					(== temp0 4)
				)
				(= local30 0)
				(= seconds 0)
				(= cycles 1)
			)
			(= temp0 0)
		)
	)
)

(class butte of Region
	(properties
		sawShadow 0
		beenCaptured 0
		policeLanded 4000
		junctioned 0
		roomCount 120
		oldPoliceRoom 0
		curPolice1Room 300
		curPolice2Room 305
		egoSwims 0
		onLedgeFlag 0
		sawGirlShadow 0
		entered315 0
	)
	
	(method (init)
		(super init: &rest)
		(= theSpeed speed)
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(or
					(== curRoomNum curPolice1Room)
					(== curRoomNum curPolice2Room)
				)
				(or
					(and (> (cop y?) 50) (< (cop y?) 150))
					(and (> (cop y?) 50) (< (cop y?) 150))
				)
				(!= (ego view?) 378)
				(!= local32 1)
			)
			(curRoom setScript: warnScript)
		)
		(cond 
			(
				(and
					(< policeLanded 0)
					(!= junctioned 1)
					(or
						(== [theOldPoliceRoom local26] curRoomNum)
						(== [theOldPoliceRoom_2 local27] curRoomNum)
					)
				)
				(= junctioned 1)
				(if (== [theOldPoliceRoom local26] curRoomNum)
					(= oldPoliceRoom [theOldPoliceRoom (- local26 1)])
				else
					(= oldPoliceRoom [theOldPoliceRoom_2 (- local27 1)])
				)
			)
			(
				(and
					(== junctioned 1)
					(!= [theOldPoliceRoom local26] curRoomNum)
					(!= [theOldPoliceRoom_2 local27] curRoomNum)
				)
				(= junctioned 0)
			)
			((< policeLanded 0)
				(if
					(and
						(localproc_08ff)
						(< (-- roomCount) 0)
						(not (cop script?))
						(!= local26 12)
					)
					(= roomCount 120)
					(if (== [theOldPoliceRoom local26] curRoomNum)
						(= oldPoliceRoom [theOldPoliceRoom local26])
					else
						(= oldPoliceRoom [theOldPoliceRoom_2 local27])
					)
					(= curPolice1Room [theOldPoliceRoom (++ local26)])
					(= curPolice2Room [theOldPoliceRoom_2 (++ local27)])
				)
			)
			(
				(and
					(not (curRoom script?))
					(not (ego edgeHit?))
					(!= curRoomNum 300)
					(!= curRoomNum 298)
					(not (-- theSpeed))
				)
				(= theSpeed speed)
				(if (not (-- policeLanded))
					(HandsOff)
					(curRoom setScript: policeLand)
				)
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(= keep
			(OneOf newRoomNumber 299 300 305 306 310 315 320 298)
		)
		(= junctioned (= initialized 0))
		(super newRoom: newRoomNumber &rest)
	)
)

(instance warnScript of rssScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cop setStep: 0 0 stopUpd:)
				(ego setStep: 0 0 stopUpd:)
				(HandsOff)
				(Animate (cast elements?) FALSE)
				(= local32 1)
				(if (== (Random 1 2) 1)
					(tSP1 modNum: BUTTE say: 1 self)
				else
					(tSP2 modNum: BUTTE say: 1 self)
				)
				(= seconds 7)
			)
			(1
				(HandsOn)
				(cop setStep: 3 2 startUpd:)
				(ego setStep: 3 2 startUpd:)
				(if (and (== prevRoomNum 531) (== curRoomNum 305))
					(client setScript: shootEgoScript)
				)
				(self dispose:)
			)
		)
	)
)

(instance butteFallScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (cast contains: cop) (cop setMotion: 0))
				(music number: 131 vol: 127 loop: 1 play:)
				(ego
					view: 300
					x:
						(switch (ego loop?)
							(0 (+ (ego x?) 10))
							(1 (- (ego x?) 10))
							(else  (ego x?))
						)
					setLoop:
					setCycle: 0
					setStep: 3 20
					setPri:
					illegalBits: 0
					setMotion: MoveTo (ego x?) 250 self
				)
			)
			(1 (= seconds 3))
			(2
				(music fade: 0 1 2 1)
				(= seconds 1)
			)
			(3 (EgoDead))
		)
	)
)

(instance policePod of Sq4View
	(properties
		x 138
		y 79
		view 305
		loop 1
		cel 10
		priority 5
		signal fixPriOn
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(narrator modNum: 530 say: 1)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance door of Sq4Prop
	(properties
		x 79
		y 55
		view 305
		loop 2
		priority 6
		signal fixPriOn
	)
)

(instance police1 of Sq4Actor
	(properties
		x 102
		y 90
		view 378
		loop 2
		signal ignrAct
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(narrator modNum: BUTTE say: 1)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance police2 of Sq4Actor
	(properties
		x 114
		y 91
		view 378
		loop 2
		signal ignrAct
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(narrator modNum: BUTTE say: 1)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance policeLand of rssScript
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if
		(and (== state 5) (== (globalSound prevSignal?) -1))
			(self cue:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: 0 stopUpd:)
				(theIconBar disable:)
				(= cycles 1)
			)
			(1
				(narrator x: 5 y: 155 modNum: BUTTE say: 2)
				(= seconds 5)
			)
			(2
				(theIconBar disable:)
				(= cycles 1)
			)
			(3
				(if modelessDialog (modelessDialog dispose:))
				(cast eachElementDo: #hide)
				(DrawPic 300 FADEOUT)
				(self cue:)
			)
			(4
				(ego setMotion: 0)
				(policePod init: stopUpd:)
				(door init: cel: 0 setPri: 7)
				(= cycles 3)
			)
			(5
				(ego setMotion: 0)
				(globalSound number: 124 loop: 1 vol: 127 play:)
			)
			(6
				(globalSound number: 142 play:)
				(door setCycle: EndLoop self)
				(police1 illegalBits: 0 setPri: 6 init:)
				(police2 illegalBits: 0 setPri: 6 init:)
			)
			(7
				(globalSound stop:)
				(ego setMotion: 0)
				(narrator x: 5 y: 155 modNum: BUTTE say: 3)
				(= seconds 4)
			)
			(8
				(ego setMotion: 0)
				(door dispose:)
				(police2 setCycle: EndLoop self)
			)
			(9
				(police2
					view: 7
					cycleSpeed: 6
					setLoop: -1
					setCycle: Walk
					setMotion: MoveTo (+ (police2 x?) 30) (police2 y?) self
				)
			)
			(10
				(police1 setCycle: EndLoop self)
			)
			(11
				(Face police2 police1)
				(police1 view: 7 cycleSpeed: 6 setLoop: -1 setPri: 12)
				(= cycles 1)
			)
			(12
				(police1
					setCycle: Walk
					setMotion: MoveTo (+ (police1 x?) 2) (police1 y?) self
				)
			)
			(13
				(if modelessDialog (modelessDialog dispose:))
				(if (== curRoomNum 305)
					(tSP1 modNum: BUTTE say: 2 self)
					(= seconds 7)
				else
					(tSP2 modNum: BUTTE say: 2 self)
					(= seconds 5)
				)
			)
			(14
				(tSP1 modNum: BUTTE say: 3 self)
				(= seconds 5)
			)
			(15
				(police2 setMotion: MoveTo 300 80)
				(if modelessDialog (modelessDialog dispose:))
				(= seconds 3)
			)
			(16
				(theIconBar disable:)
				(= cycles 1)
			)
			(17
				(ego setMotion: 0)
				(policePod dispose: delete:)
				(if modelessDialog (modelessDialog dispose:))
				(DrawPic curRoomNum FADEOUT)
				(police1 dispose: delete:)
				(police2 dispose: delete:)
				(ego show: setCycle: StopWalk 4)
				(if (== curRoomNum 305) ((ScriptID 305 1) show:))
				(cast eachElementDo: #show)
				((ego _head?) show:)
				(= cycles 1)
			)
			(18
				(theIconBar enable:)
				(HandsOn)
				(curRoom setScript: 0)
			)
		)
	)
)

(instance cop of Sq4Actor
	(properties
		view 7
		priority 8
		signal (| ignrHrz fixPriOn)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(narrator modNum: BUTTE say: 1)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance shootEgoScript of Script
	(properties)
	
	(method (changeState newState)
		(asm
			lap      newState
			aTop     state
			push    
			dup     
			ldi      0
			eq?     
			bnt      code_099f
			pushi    0
			callb    HandsOff,  0
			pushi    #setMotion
			pushi    1
			pushi    0
			lag      ego
			send     6
			pushi    0
			call     localproc_087f,  0
			pushi    4
			pushi    #x
			pushi    0
			lofsa    cop
			send     4
			push    
			pushi    #y
			pushi    0
			lofsa    cop
			send     4
			push    
			pushi    #x
			pushi    0
			lag      ego
			send     4
			push    
			pushi    #y
			pushi    0
			lag      ego
			send     4
			push    
			callk    GetAngle,  8
			sal      local31
			push    
			ldi      110
			gt?     
			bnt      code_0998
			lsl      local31
			ldi      235
			lt?     
			bnt      code_0998
			pushi    #loop
			pushi    0
			lofsa    cop
			send     4
			push    
			ldi      2
			ne?     
			bnt      code_0998
			pushi    #loop
			pushi    1
			pushi    2
			lofsa    cop
			send     6
code_0998:
			ldi      2
			aTop     cycles
			jmp      code_0c4b
code_099f:
			dup     
			ldi      1
			eq?     
			bnt      code_09c9
			pushi    #setMotion
			pushi    1
			pushi    0
			lag      ego
			send     6
			pushi    #view
			pushi    1
			pushi    13
			pushi    4
			pushi    1
			pushi    0
			pushi    161
			pushi    4
			class    CycleTo
			push    
			pushi    1
			pushi    1
			pushSelf
			lofsa    cop
			send     24
			jmp      code_0c4b
code_09c9:
			dup     
			ldi      2
			eq?     
			bnt      code_0b43
			pushi    #setMotion
			pushi    1
			pushi    0
			lag      ego
			send     6
			pushi    #setCycle
			pushi    1
			class    EndLoop
			push    
			lofsa    cop
			send     6
			pushi    #distanceTo
			pushi    1
			lofsa    cop
			push    
			lag      ego
			send     6
			push    
			ldi      40
			gt?     
			bnt      code_0b3a
			lsg      curRoomNum
			ldi      310
			eq?     
			bnt      code_0a18
			pushi    #y
			pushi    0
			lag      ego
			send     4
			push    
			ldi      133
			gt?     
			bnt      code_0a18
			pushi    #priority
			pushi    0
			lag      ego
			send     4
			push    
			ldi      13
			lt?     
code_0a18:
			not     
			bnt      code_0b3a
			pushi    #loop
			pushi    0
			lofsa    cop
			send     4
			push    
			dup     
			ldi      0
			eq?     
			bnt      code_0a48
			pushi    #x
			pushi    0
			lofsa    cop
			send     4
			push    
			ldi      30
			add     
			sal      local28
			pushi    #y
			pushi    0
			lofsa    cop
			send     4
			push    
			ldi      29
			sub     
			sal      local29
			jmp      code_0aeb
code_0a48:
			dup     
			ldi      1
			eq?     
			bnt      code_0a6b
			pushi    #x
			pushi    0
			lofsa    cop
			send     4
			push    
			ldi      35
			sub     
			sal      local28
			pushi    #y
			pushi    0
			lofsa    cop
			send     4
			push    
			ldi      27
			sub     
			sal      local29
			jmp      code_0aeb
code_0a6b:
			dup     
			ldi      2
			eq?     
			bnt      code_0a8a
			pushi    #x
			pushi    0
			lofsa    cop
			send     4
			sal      local28
			pushi    #y
			pushi    0
			lofsa    cop
			send     4
			push    
			ldi      27
			sub     
			sal      local29
			jmp      code_0aeb
code_0a8a:
			dup     
			ldi      3
			eq?     
			bnt      code_0aa9
			pushi    #x
			pushi    0
			lofsa    cop
			send     4
			sal      local28
			pushi    #y
			pushi    0
			lofsa    cop
			send     4
			push    
			ldi      40
			sub     
			sal      local29
			jmp      code_0aeb
code_0aa9:
			dup     
			ldi      4
			eq?     
			bnt      code_0acb
			pushi    #x
			pushi    0
			lofsa    cop
			send     4
			push    
			ldi      20
			add     
			sal      local28
			pushi    #y
			pushi    0
			lofsa    cop
			send     4
			push    
			ldi      27
			sub     
			sal      local29
			jmp      code_0aeb
code_0acb:
			dup     
			ldi      5
			eq?     
			bnt      code_0aeb
			pushi    #x
			pushi    0
			lofsa    cop
			send     4
			push    
			ldi      20
			sub     
			sal      local28
			pushi    #y
			pushi    0
			lofsa    cop
			send     4
			push    
			ldi      27
			sub     
			sal      local29
code_0aeb:
			toss    
			pushi    #x
			pushi    0
			lofsa    cop
			send     4
			push    
			pushi    #x
			pushi    0
			lag      ego
			send     4
			lt?     
			bnt      code_0b1c
			pushi    4
			lsl      local29
			lsl      local28
			pushi    #y
			pushi    0
			lag      ego
			send     4
			push    
			ldi      32
			sub     
			push    
			pushi    #x
			pushi    0
			lag      ego
			send     4
			push    
			call     localproc_0c4d,  8
			jmp      code_0b3c
code_0b1c:
			pushi    4
			pushi    #y
			pushi    0
			lag      ego
			send     4
			push    
			ldi      32
			sub     
			push    
			pushi    #x
			pushi    0
			lag      ego
			send     4
			push    
			lsl      local29
			lsl      local28
			call     localproc_0c4d,  8
			jmp      code_0b3c
code_0b3a:
			ldi      0
code_0b3c:
			ldi      2
			aTop     cycles
			jmp      code_0c4b
code_0b43:
			dup     
			ldi      3
			eq?     
			bnt      code_0bb1
			pushi    #setMotion
			pushi    1
			pushi    0
			lag      ego
			send     6
			pushi    2
			pushi    1
			pushi    26
			pushi    3
			pushi    1
			pushi    #loop
			pushi    0
			lag      ego
			send     4
			push    
			dup     
			ldi      4
			eq?     
			bnt      code_0b6d
			ldi      0
			jmp      code_0b93
code_0b6d:
			dup     
			ldi      5
			eq?     
			bnt      code_0b78
			ldi      1
			jmp      code_0b93
code_0b78:
			dup     
			ldi      6
			eq?     
			bnt      code_0b82
			ldi      0
			jmp      code_0b93
code_0b82:
			dup     
			ldi      7
			eq?     
			bnt      code_0b8c
			ldi      1
			jmp      code_0b93
code_0b8c:
			pushi    #loop
			pushi    0
			lag      ego
			send     4
code_0b93:
			toss    
			push    
			pushi    4
			pushi    1
			pushi    0
			pushi    247
			pushi    1
			pushi    6
			pushi    161
			pushi    4
			class    CycleTo
			push    
			pushi    2
			pushi    1
			pushSelf
			lag      ego
			send     36
			jmp      code_0c4b
code_0bb1:
			dup     
			ldi      4
			eq?     
			bnt      code_0bdd
			pushi    #setMotion
			pushi    1
			pushi    0
			lag      ego
			send     6
			pushi    #view
			pushi    1
			pushi    7
			lofsa    cop
			send     6
			pushi    #setCycle
			pushi    4
			class    CycleTo
			push    
			pushi    1
			pushi    65535
			pushSelf
			lag      ego
			send     12
			jmp      code_0c4b
code_0bdd:
			dup     
			ldi      5
			eq?     
			bnt      code_0bfc
			pushi    #setMotion
			pushi    1
			pushi    0
			lag      ego
			send     6
			pushi    #setCycle
			pushi    2
			class    EndLoop
			push    
			pushSelf
			lag      ego
			send     8
			jmp      code_0c4b
code_0bfc:
			dup     
			ldi      6
			eq?     
			bnt      code_0c12
			pushi    #setMotion
			pushi    1
			pushi    0
			lag      ego
			send     6
			ldi      3
			aTop     seconds
			jmp      code_0c4b
code_0c12:
			dup     
			ldi      7
			eq?     
			bnt      code_0c21
			pushi    1
			pushi    8
			callb    EgoDead,  2
			jmp      code_0c4b
code_0c21:
			dup     
			ldi      8
			eq?     
			bnt      code_0c4b
			pushi    #script
			pushi    0
			lag      curRoom
			send     4
			not     
			bnt      code_0c42
			pushi    1
			pushi    8
			callb    EgoDead,  2
			pushi    #view
			pushi    1
			pushi    7
			lofsa    cop
			send     6
code_0c42:
			pushi    0
			callb    HandsOn,  0
			pushi    #dispose
			pushi    0
			self     4
code_0c4b:
			toss    
			ret     
		)
	)
)

(instance theRoom of Sq4Feature
	(properties
		nsBottom 200
		nsRight 320
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_TALK
				(if (not (curRoom script?))
					(curRoom setScript: sEcho)
				else
					0
				)
			)
			(else  0)
		)
	)
)

(instance sEcho of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 3])
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: 0 setCycle: 0)
				(= cycles 2)
			)
			(1
				(ego view: 297 setCel: 0)
				(= ticks 30)
			)
			(2 (ego setCycle: EndLoop self))
			(3 (tRogNar say: 2 self))
			(4 (ego setCycle: BegLoop self))
			(5
				(ego
					view: 0
					loop: 3
					normal: 1
					moveHead: 1
					setLoop: -1
					setLoop: theStopGroop
					setMotion: 0
					setCycle: StopWalk 4
					setStep: 3 2
					illegalBits: 0
					ignoreActors: 0
					setSpeed: speed
				)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance tRogNar of Sq4Narrator
	(properties
		noun 7
		modNum 297
		disposeWhenDone 0
		talkerNum ROGER
	)
)

(instance tSP1 of Sq4Talker
	(properties
		z 400
		noun 22
		modNum BUTTE
		view 1015
		talkerNum SP1
		mouthOffsetX 24
		mouthOffsetY 34
		eyeOffsetX 1
		eyeOffsetY 1
	)
)

(instance tSP2 of Sq4Talker
	(properties
		z 400
		noun 14
		modNum BUTTE
		view 1016
		talkerNum SP2
		mouthOffsetX 21
		mouthOffsetY 34
		eyeOffsetX 1
		eyeOffsetY 1
	)
)
