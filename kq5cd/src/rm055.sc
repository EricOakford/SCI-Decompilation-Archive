;;; Sierra Script 1.0 - (do not remove this comment)
(script# 55)
(include game.sh)
(use Main)
(use Intrface)
(use dropInScript)
(use Door)
(use KQ5Room)
(use Sync)
(use RandCyc)
(use PolyPath)
(use LoadMany)
(use RFeature)
(use Motion)
(use Actor)
(use System)

(public
	rm055 0
	priBlock 1
)

(local
	[local0 3] = [1 0 50]
	[local3 19] = [7 -3 5 -7 7 -3 7 -2 5 3 5 5 7 12 2 26 0 18 -32768]
	hairPinX
	hairPinY
	local24
	local25
	[local26 43] = [8 6 0 7 6 1 7 7 3 6 7 2 5 7 3 5 6 2 5 5 3 6 5 1 6 4 3 7 4 1 7 3 0 7 2 1 6 2 2 5 2 3 -32768]
	[local69 4] = [126 89 205 228]
	[local73 4] = [121 136 121 136]
	[local77 4] = [88 53 237 264]
	[local81 4] = [121 136 121 136]
	local85
	local86
	local87
	local88
	[local89 9] = [155 9 2 155 50 1 38 50 -32768]
	[local98 9] = [155 9 2 155 50 0 270 50 -32768]
	[local107 15] = [155 9 2 155 50 1 90 50 2 90 85 1 38 85 -32768]
	[local122 15] = [155 9 2 155 50 0 231 50 2 231 85 0 270 85 -32768]
	[local137 15] = [38 85 0 90 85 3 90 50 0 155 50 3 155 9 -32768]
	[local152 12] = [38 85 0 90 85 3 90 50 0 270 50 -32768]
	[local164 12] = [270 85 1 231 85 3 231 50 1 38 50 -32768]
)
(procedure (localproc_02e8 &tmp temp0)
	(theGame setCursor: waitCursor)
	(curRoom drawPic: 55)
	(addToPics eachElementDo: #dispose)
	(addToPics eachElementDo: #delete)
	(addToPics dispose:)
	(hole dispose:)
	(cell dispose:)
	(door dispose:)
	(doorBack dispose:)
	(dink dispose:)
	(dinkTalk dispose:)
	(dinkHair dispose:)
	(dinkBlink dispose:)
	(hairPin dispose:)
	(mazeBug dispose:)
	(helper dispose:)
	(arrow dispose:)
	(if (!= (theMusic number?) 25)
		(theMusic loop: -1 number: 25 playBed:)
	)
	(ego illegalBits: 0)
	(localproc_03be)
	(if (== (cass script?) cassScript) (Bset 110))
)

(procedure (localproc_03be)
	(= global383 (mod global383 360))
	(ego posn: 161 145 0 setMotion: 0 heading: 0 loop: 3)
	(if (== prevRoomNum 55)
		(switch global383
			(0 (-- global385))
			(90 (++ global384))
			(180 (++ global385))
			(270 (-- global384))
		)
	)
	(proc603_19)
	(proc603_14)
	(proc603_15)
	(proc603_16)
	(proc603_17)
	(proc603_6)
	(proc603_7)
	(proc603_8)
	(proc603_9)
	(proc603_10)
	(proc603_11)
	(proc603_12)
	(proc603_13)
	(cond 
		((localproc_070c) 0)
		((localproc_07a6) 0)
		((localproc_04f6) 0)
		((localproc_0640) 0)
		((localproc_069b) 0)
		((< (theGame detailLevel:) 3) 0)
		((localproc_07dd) 0)
	)
	(theGame setCursor: ((theIconBar curIcon?) cursor?))
)

(procedure (localproc_04f6)
	(asm
		lsg      global384
		ldi      3
		eq?     
		bnt      code_0505
		lsg      global385
		ldi      5
		eq?     
code_0505:
		not     
		bnt      code_055b
		lsg      global384
		ldi      3
		eq?     
		bnt      code_0518
		lsg      global385
		ldi      4
		eq?     
code_0518:
		not     
		bnt      code_055b
		lsg      global384
		ldi      3
		eq?     
		bnt      code_052b
		lsg      global385
		ldi      6
		eq?     
code_052b:
		not     
		bnt      code_055b
		lsg      global384
		ldi      2
		eq?     
		bnt      code_053e
		lsg      global385
		ldi      5
		eq?     
code_053e:
		not     
		bnt      code_055b
		lsg      global384
		ldi      4
		eq?     
		bnt      code_0551
		lsg      global385
		ldi      5
		eq?     
code_0551:
		not     
		bnt      code_055b
		ldi      0
		ret     
		jmp      code_063f
code_055b:
		lsg      global384
		ldi      3
		eq?     
		bnt      code_059f
		lsg      global385
		ldi      5
		eq?     
		bnt      code_059f
		pushi    #view
		pushi    1
		pushi    878
		pushi    160
		pushi    1
		pushi    5
		pushi    7
		pushi    1
		pushi    2
		pushi    66
		pushi    1
		pushi    12
		pushi    4
		pushi    1
		pushi    162
		pushi    3
		pushi    1
		pushi    48
		pushi    107
		pushi    0
		pushi    192
		pushi    0
		lofsa    hole
		send     44
		ldi      1
		ret     
		jmp      code_063f
code_059f:
		lsg      global384
		ldi      3
		eq?     
		bnt      code_05bb
		lsg      global385
		ldi      4
		eq?     
		bnt      code_05bb
		lsg      global383
		ldi      180
		ne?     
		bt       code_060d
code_05bb:
		lsg      global384
		ldi      3
		eq?     
		bnt      code_05d6
		lsg      global385
		ldi      6
		eq?     
		bnt      code_05d6
		lsg      global383
		ldi      0
		ne?     
		bt       code_060d
code_05d6:
		lsg      global384
		ldi      2
		eq?     
		bnt      code_05f1
		lsg      global385
		ldi      5
		eq?     
		bnt      code_05f1
		lsg      global383
		ldi      90
		ne?     
		bt       code_060d
code_05f1:
		lsg      global384
		ldi      4
		eq?     
		bnt      code_0613
		lsg      global385
		ldi      5
		eq?     
		bnt      code_0613
		lsg      global383
		ldi      270
		ne?     
		bnt      code_0613
code_060d:
		ldi      0
		ret     
		jmp      code_063f
code_0613:
		pushi    #view
		pushi    1
		pushi    878
		pushi    160
		pushi    1
		pushi    5
		pushi    7
		pushi    1
		pushi    3
		pushi    66
		pushi    1
		pushi    12
		pushi    4
		pushi    1
		pushi    162
		pushi    3
		pushi    1
		pushi    55
		pushi    107
		pushi    0
		lofsa    hole
		send     40
		ldi      1
		ret     
code_063f:
		ret     
	)
)

(procedure (localproc_0640)
	(return
		(cond 
			((not (Btst 67)) (return 0))
			((and (== global384 8) (== global385 6))
				(if (== global383 90)
					(cell
						view: 878
						setLoop: 5
						cel: 0
						setPri: 11
						x: 162
						y: 119
						init:
						show:
					)
				)
				(return 1)
			)
		)
	)
)

(procedure (localproc_069b)
	(return
		(if
			(and
				(== global384 5)
				(== global385 1)
				(== global383 0)
			)
			(door
				view: 862
				setLoop: 0
				cel: 0
				setPri: 11
				x: 162
				y: 128
				init:
				show:
				stopUpd:
			)
			(doorBack x: 162 y: 128 init: stopUpd: show:)
			(return 1)
		else
			(return 0)
		)
	)
)

(procedure (localproc_070c)
	(return
		(cond 
			((not (ego has: 34)) (return 0))
			(
				(or
					(and (== global384 1) (== global385 7))
					(and (== global384 3) (== global385 1))
					(and (== global384 8) (== global385 1))
					(and (== global384 8) (== global385 8))
				)
				(theMusic fade:)
				(dink init: setScript: mainDinkScript)
				(dinkHair init:)
				(ego x: (- (ego x?) 30))
				(return 1)
			)
			(else (return 0))
		)
	)
)

(procedure (localproc_07a6)
	(return
		(if
		(and (== global384 local24) (== global385 local25))
			(hairPin
				init:
				posn: hairPinX hairPinY 0
				setCycle: 0
				stopUpd:
			)
			(return 1)
		else
			(return 0)
		)
	)
)

(procedure (localproc_07dd)
	(return
		(if
			(and
				(> (Random 0 99) 89)
				(== (theGame detailLevel:) 3)
				(not (cass script?))
			)
			(mazeBugScript start: 0)
			(mazeBug init: setScript: mazeBugScript)
			(return 1)
		else
			(return 0)
		)
	)
)

(instance rm055 of KQ5Room
	(properties
		picture 55
	)
	
	(method (init)
		(LoadMany 130 603)
		(LoadMany 128 877 878 678 862 861 865 871 872 876)
		(Load SOUND 78)
		(Load VIEW 874 1060)
		(LoadMany 132 888 889 890 50)
		(super init:)
		(ego
			init:
			view: 0
			setLoop: -1
			loop: 3
			posn: 161 145 0
			setMotion: 0
			setCycle: KQ5SyncWalk
			cycleSpeed: 0
			setStep: 3 2
			setPri: 11
		)
		(switch prevRoomNum
			(56
				(arrow cel: 2)
				(= global384 5)
				(= global385 1)
				(= global383 0)
				(ego heading: 0)
			)
			(67
				(= global384 8)
				(= global385 6)
				(arrow cel: 1)
				(if (Btst 69)
					(cass
						init:
						setCycle: Walk
						setScript: cassScript
						posn: [local69 [local26 2]] [local73 [local26 2]] 0
						setPri: 8
						loop: 1
					)
					(= global383 270)
					(ego observeControl: 8 heading: 0)
				else
					(= global383 90)
					(ego heading: 0)
				)
				(Bset 67)
			)
			(else 
				(= global384 3)
				(= global385 5)
				(arrow cel: 3)
				(= global383 0)
				(ego heading: 0)
				(HandsOff)
				(self setScript: (ScriptID 603 21))
			)
		)
		(localproc_02e8)
		(= prevRoomNum 55)
		(theMusic loop: -1 number: 25 play:)
		(if (== script (ScriptID 603 21))
			((ScriptID 603 21) cue:)
		)
		(self setFeatures: helpFeat room)
	)
	
	(method (doit &tmp temp0)
		(= temp0 (ego onControl: 1))
		(cond 
			(script (script doit:))
			((== temp0 8) (= global383 (+ global383 180)) (localproc_02e8))
			((== temp0 1024) (localproc_02e8))
			((& temp0 $0040) (= global383 (+ global383 90)) (localproc_02e8))
			((& temp0 $0002) (= global383 (+ global383 270)) (localproc_02e8))
		)
	)
	
	(method (dispose)
		(theMusic fade:)
		(DisposeScript 969)
		(DisposeScript 941)
		(DisposeScript 603)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp [temp0 100])
		(cond 
			((event claimed?) (return))
			(script (return))
		)
	)
)

(instance cassScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (Btst 110)
			(Bclr 110)
			(if (not (Btst 69)) (cass z: 1000))
			(ego loop: 3)
			(if (not (Btst 69))
				(if (<= state 2)
					(= cycles 0)
					(= start 4)
					(self init:)
				)
			else
				(self cue:)
				(Bclr 69)
			)
		)
		(if (& (cass onControl: 1) $0020) (cass priority: 8))
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 0)
			(1
				(HandsOff)
				(cass
					posn:
						[local69 [local26 (+ register 2)]]
						[local73 [local26 (+ register 2)]]
						0
					setPri: 10
					setMotion:
						MoveTo
						[local77 [local26 (+ register 2)]]
						[local81 [local26 (+ register 2)]]
						self
				)
			)
			(2
				(HandsOn)
				(cass z: 1000)
				(= cycles 120)
			)
			(3
				(= register 997)
				(= cycles 1)
			)
			(4
				(= register (+ register 3))
				(cond 
					((== [local26 register] -32768)
						(cass
							setPri: (- (door priority?) 1)
							posn: (- (door x?) 20) (door y?) 0
						)
						(HandsOff)
						(= cycles 1)
					)
					(
						(or
							(== register 1000)
							(!= global384 [local26 register])
							(!= global385 [local26 (+ register 1)])
						)
						(cass dispose:)
						(self dispose:)
					)
					(else (= cycles 0) (= start 1) (self init:))
				)
			)
			(5
				(Bset 68)
				(door setCel: (door lastCel:))
				(cass
					setMotion: MoveTo (+ (door x?) 2) (- (door y?) 2) self
				)
			)
			(6
				(theAudio number: 9300 play:)
				(door setCycle: BegLoop self)
			)
			(7
				(HandsOn)
				(cass z: 1000)
				(self dispose:)
			)
		)
	)
)

(instance mainDinkScript of Script
	(properties)
	
	(method (doit &tmp temp0 theRegister)
		(super doit: &rest)
		(cond 
			(
				(and
					(<=
						270
						(= temp0
							(GetAngle (client x?) (client y?) (ego x?) (ego y?))
						)
					)
					(<= temp0 360)
				)
				(= theRegister 1)
			)
			((and (<= 255 temp0) (<= temp0 269)) (= theRegister 2))
			((and (<= 106 temp0) (<= temp0 255)) (= theRegister 3))
			((and (<= 89 temp0) (<= temp0 105)) (= theRegister 4))
			((and (<= 0 temp0) (<= temp0 90)) (= theRegister 5))
		)
		(if (!= theRegister register)
			(switch theRegister
				(1
					(client loop: 4 cel: 0 setCycle: EndLoop)
				)
				(2
					(if (== register 1)
						(client setCycle: BegLoop)
					else
						(client loop: 0 cel: 0 setCycle: EndLoop)
					)
				)
				(3
					(if (== register 2)
						(client setLoop: 0)
						(client cel: (client lastCel:) setCycle: BegLoop)
					else
						(client setLoop: 1)
						(client cel: (client lastCel:) setCycle: BegLoop)
					)
				)
				(4
					(if (== register 5)
						(client setCycle: BegLoop)
					else
						(client loop: 1 cel: 0 setCycle: EndLoop)
					)
				)
				(5
					(client loop: 5 cel: 0 setCycle: EndLoop)
				)
			)
			(= register theRegister)
		)
		(if
			(and
				(not (curRoom script?))
				(ego inRect: 150 120 319 189)
			)
			(HandsOff)
			(curRoom setScript: hugScript 0 theRegister)
		)
	)
	
	(method (dispose)
		(if (not (dink script?))
			(theMusic loop: -1 number: 25 playBed:)
		)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic number: 887 loop: -1 playBed:)
				(= register 3)
			)
		)
	)
)

(instance hugScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(SpeakAudio 9000 self)
				(if (== (dink cel?) 6)
					(dinkTalk init: setCycle: MouthSync 9000)
				)
			)
			(1
				(if (IsObject dinkTalk) (dinkTalk setCycle: 0 dispose:))
				(ego setPri: (+ (dink priority?) 1))
				(dink
					setScript: 0
					setLoop: 6
					cel: 0
					cycleSpeed: 3
					setCycle: CycleTo 3 1 self
				)
			)
			(2
				((ego head?) dispose:)
				(ego dispose:)
				(dink setCycle: EndLoop self)
			)
			(3
				(= seconds 1)
				(Load VIEW 545)
				(= inCartoon 0)
			)
			(4 (cls) (= seconds 3))
			(5
				(= deathMessage 574)
				(EgoDead 545)
			)
		)
	)
)

(instance giveTamboScript of Script
	(properties)
	
	(method (changeState newState &tmp dinkX dinkY)
		(switch (= state newState)
			(0
				(ego put: 34)
				(HandsOff)
				(SolvePuzzle 3)
				(ego
					setPri: (+ (dink priority?) 1)
					setMotion: PolyPath (- (dink x?) 20) (dink y?) self
				)
				(if (and (== (dink loop?) 4) (!= (dink cel?) 0))
					(dink setCycle: BegLoop self)
				else
					(= cycles 1)
				)
			)
			(1 0)
			(2
				((ego head?) hide:)
				(ego
					normal: 0
					view: 874
					setLoop: 5
					cel: 0
					cycleSpeed: 1
					setPri: (+ (dink priority?) 1)
					setCycle: RandCycle 30 self
				)
				(theAudio number: 8050 loop: -1 play:)
			)
			(3
				(theAudio stop:)
				(dink setScript: 0 ignoreActors: 1)
				(if (== (dink loop?) 4)
					(= dinkX (- (dink x?) 3))
					(= dinkY (+ (dink y?) 2))
				else
					(= dinkX (dink x?))
					(= dinkY (dink y?))
				)
				(ego
					view: 1060
					setLoop: 1
					x: dinkX
					y: dinkY
					cel: 0
					cycleSpeed: 1
					setPri: (+ (dink priority?) 1)
					setCycle: EndLoop self
				)
				(UnLoad 132 50)
			)
			(4
				(ego
					normal: 1
					view: 0
					x: (- (dink x?) 20)
					y: (dink y?)
					cycleSpeed: 0
					setCycle: KQ5SyncWalk
					moveSpeed: (theGame egoMoveSpeed?)
					setScript: moveEgoScript
				)
				(theMusic number: 890 loop: -1 playBed:)
				(dink
					view: 874
					setLoop: 2
					cel: 0
					cycleSpeed: 3
					setCycle: EndLoop self
				)
			)
			(5 (dink setCycle: EndLoop self))
			(6 (dink setCycle: EndLoop self))
			(7
				(dink view: 874 setLoop: 3 cel: 0 setCycle: Forward)
				(= seconds 5)
			)
			(8
				(theMusic number: 888 loop: 1 playBed:)
				(UnLoad 132 890)
				(DoAudio 1 8889)
				(dink view: 1060 setLoop: 0 cel: 0 setCycle: EndLoop self)
			)
			(9
				(theAudio number: 8889 loop: 1 play:)
				(UnLoad 132 888)
				(hairPin init:)
				(self setScript: hairPinScript self)
			)
			(10
				(= hairPinX (hairPin x?))
				(= hairPinY (hairPin y?))
				(= local24 global384)
				(= local25 global385)
				(dink view: 873 setLoop: 4 cel: 0 setCycle: EndLoop self)
			)
			(11
				(dink
					setLoop: 3
					setPri: 10
					setCycle: Walk
					setMotion: MoveTo (- (dink x?) 75) (+ (dink y?) 75) self
				)
				(theMusic fade:)
			)
			(12
				(theMusic loop: -1 number: 25 playBed:)
				(hairPin setCycle: 0 ignoreActors: 1)
				(HandsOn)
				(UnLoad 132 889)
			)
			(13
				(dink dispose:)
				(self dispose:)
			)
		)
	)
)

(instance moveEgoScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ego head?) show:)
				(ego setMotion: MoveTo (- (ego x?) 45) (ego y?) self)
			)
			(1
				(ego loop: 0 setLoop: -1)
				((ego head?) show:)
				(self dispose:)
			)
		)
	)
)

(instance hairPinScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(hairPin
					posn:
						(+ (hairPin x?) [local3 register])
						(+ (hairPin y?) [local3 (++ register)])
				)
				(= cycles 1)
			)
			(1
				(if (== [local3 (++ register)] -32768)
					(= cycles 1)
				else
					(self init:)
				)
			)
			(2
				(theAudio number: 8797 loop: 1 play:)
				(client cue:)
				(= seconds 3)
			)
			(3
				(hairPin setCycle: 0 ignoreActors: 1 stopUpd:)
				(client setScript: 0)
			)
		)
	)
)

(instance goHoleScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 162 131 self)
			)
			(1
				((ego head?) dispose:)
				(ego
					normal: 0
					view: 861
					setLoop: 0
					cel: 0
					cycleSpeed: 3
					setCycle: EndLoop self
				)
			)
			(2
				(ego dispose:)
				(curRoom newRoom: 67)
			)
		)
	)
)

(instance goDoorScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(LoadMany 132 122 124)
				(ego
					setMotion: MoveTo (- (door x?) 4) (+ (door y?) 6) self
				)
			)
			(1
				((ego head?) hide:)
				(ego
					normal: 0
					view: 678
					setLoop: 0
					cycleSpeed: 2
					cel: 0
					setCycle: EndLoop self
				)
				(door setCycle: EndLoop)
			)
			(2
				(ego
					normal: 1
					view: 0
					setLoop: -1
					cycleSpeed: 0
					loop: 3
					setCycle: KQ5SyncWalk
				)
				((ego head?) show:)
				(= cycles 2)
			)
			(3
				(ego setMotion: MoveTo (- (ego x?) 16) (ego y?) self)
			)
			(4
				(ego
					illegalBits: 0
					setPri: (- (door priority?) 1)
					setMotion: MoveTo (+ (door x?) 2) (- (door y?) 1) self
				)
			)
			(5
				(ego z: 1000)
				(door setCycle: BegLoop self)
			)
			(6
				(if (> (DoAudio 6) -1) (-- state))
				(= cycles 1)
			)
			(7 (curRoom newRoom: 56))
		)
	)
)

(instance lockedDoorScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					setMotion: MoveTo (- (door x?) 4) (+ (door y?) 6) self
				)
			)
			(1
				((ego head?) hide:)
				(ego
					normal: 0
					view: 678
					setLoop: 2
					cycleSpeed: 2
					setCycle: EndLoop self
				)
			)
			(2 (= cycles 10))
			(3 (ego setCycle: BegLoop self))
			(4 (ego setCycle: EndLoop self))
			(5 (= cycles 10))
			(6 (ego setCycle: BegLoop self))
			(7
				((ego head?) show:)
				(ego
					normal: 1
					view: 0
					setLoop: -1
					loop: 3
					cycleSpeed: 0
					setCycle: KQ5SyncWalk
				)
				(= seconds 2)
			)
			(8
				(SpeakAudio 582)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance pickLockScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(SolvePuzzle 4)
				(ego
					setMotion: MoveTo (- (door x?) 4) (+ (door y?) 6) self
				)
			)
			(1
				((ego head?) hide:)
				(ego
					normal: 0
					view: 678
					setLoop: 1
					setCycle: RandCycle 15 self
				)
			)
			(2
				((ego head?) show:)
				(ego normal: 1 view: 0 setLoop: -1 setCycle: KQ5SyncWalk)
				(= seconds 1)
			)
			(3
				(SpeakAudio 587)
				(Bset 68)
				(HandsOn)
				(= inCartoon 0)
				(self dispose:)
			)
		)
	)
)

(instance getHairPinScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					setMotion: MoveTo (- (hairPin x?) 10) (hairPin y?) self
				)
			)
			(1
				((ego head?) hide:)
				(ego normal: 0 view: 56 setLoop: 2 setCycle: EndLoop self)
			)
			(2
				(SolvePuzzle 2)
				(ego get: 36 setCycle: BegLoop self)
				(hairPin dispose:)
				(= local24 0)
				(= local25 0)
			)
			(3
				((ego head?) show:)
				(ego normal: 1 view: 0 setLoop: -1 setCycle: KQ5SyncWalk)
				(SpeakAudio 583)
				(HandsOn)
				(= inCartoon 0)
				(self dispose:)
			)
		)
	)
)

(instance mazeBugScript of Script
	(properties)
	
	(method (dispose)
		(theMusic2 stop:)
		(theAudio stop:)
		(mazeBug z: 1000)
		(= start 0)
		(super dispose:)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(switch (Random 0 2)
					(0 (mazeBug view: 871))
					(1 (mazeBug view: 872))
					(2 (mazeBug view: 876))
				)
				(= cycles 5)
			)
			(1
				(= local85 0)
				(if
					(==
						(proc603_18 (proc603_3 -1 0 1 0) (proc603_4 0 -1 0 1))
						1
					)
					(= local85 (| local85 $0002))
				)
				(if
					(==
						(proc603_18 (proc603_3 1 0 -1 0) (proc603_4 0 1 0 -1))
						1
					)
					(= local85 (| local85 $0001))
				)
				(cond 
					((and (& local85 $0002) (& local85 $0001)) (= local85 (+ (Random 0 2) 2)))
					(
					(and (not (& local85 $0002)) (not (& local85 $0001))) (= local85 (Random 0 1)))
					(
					(and (not (& local85 $0002)) (& local85 $0001)) (= local85 (* (Random 0 1) 6)))
					(else
						(= local85
							(+ (= temp0 (Random 1 4)) (* 1 (> temp0 2)))
						)
					)
				)
				(switch local85
					(0
						(= local86 [local89 0])
						(= local87 [local89 1])
						(= local88 [local89 2])
					)
					(1
						(= local86 [local98 0])
						(= local87 [local98 1])
						(= local88 [local98 2])
					)
					(2
						(= local86 [local107 0])
						(= local87 [local107 1])
						(= local88 [local107 2])
					)
					(3
						(= local86 [local122 0])
						(= local87 [local122 1])
						(= local88 [local122 2])
					)
					(4
						(= local86 [local137 0])
						(= local87 [local137 1])
						(= local88 [local137 2])
					)
					(5
						(= local86 [local152 0])
						(= local87 [local152 1])
						(= local88 [local152 2])
					)
					(6
						(= local86 [local164 0])
						(= local87 [local164 1])
						(= local88 [local164 2])
					)
				)
				(theAudio number: 8150 loop: -1 play:)
				(mazeBug
					posn: local86 local87 0
					setLoop: local88
					priority: 11
					show:
					setCycle: Forward
				)
				(= register 3)
				(= cycles 1)
			)
			(2
				(switch local85
					(0
						(= local86 [local89 register])
						(= local87 [local89 (++ register)])
					)
					(1
						(= local86 [local98 register])
						(= local87 [local98 (++ register)])
					)
					(2
						(= local86 [local107 register])
						(= local87 [local107 (++ register)])
					)
					(3
						(= local86 [local122 register])
						(= local87 [local122 (++ register)])
					)
					(4
						(= local86 [local137 register])
						(= local87 [local137 (++ register)])
					)
					(5
						(= local86 [local152 register])
						(= local87 [local152 (++ register)])
					)
					(6
						(= local86 [local164 register])
						(= local87 [local164 (++ register)])
					)
				)
				(mazeBug setMotion: MoveTo local86 local87 self)
				(++ register)
			)
			(3
				(switch local85
					(0
						(= local88 [local89 register])
						(if (== [local89 register] -32768) (= register -1))
					)
					(1
						(= local88 [local98 register])
						(if (== [local98 register] -32768) (= register -1))
					)
					(2
						(= local88 [local107 register])
						(if (== [local107 register] -32768) (= register -1))
					)
					(3
						(= local88 [local122 register])
						(if (== [local122 register] -32768) (= register -1))
					)
					(4
						(= local88 [local137 register])
						(if (== [local137 register] -32768) (= register -1))
					)
					(5
						(= local88 [local152 register])
						(if (== [local152 register] -32768) (= register -1))
					)
					(6
						(= local88 [local164 register])
						(if (== [local164 register] -32768) (= register -1))
					)
				)
				(if (== register -1)
					(self dispose:)
				else
					(mazeBug setLoop: local88)
					(++ register)
					(= start 2)
					(self init:)
				)
			)
		)
	)
)

(instance helpScript of Script
	(properties)
	
	(method (doit &tmp arrowCel)
		(switch (ego loop?)
			(7 (= arrowCel (arrow cel?)))
			(3
				(switch global383
					(0 (= arrowCel 3))
					(90 (= arrowCel 1))
					(180 (= arrowCel 4))
					(270 (= arrowCel 2))
				)
			)
			(0
				(switch global383
					(0 (= arrowCel 1))
					(90 (= arrowCel 4))
					(180 (= arrowCel 2))
					(270 (= arrowCel 3))
				)
			)
			(2
				(switch global383
					(0 (= arrowCel 4))
					(90 (= arrowCel 2))
					(180 (= arrowCel 3))
					(270 (= arrowCel 1))
				)
			)
			(1
				(switch global383
					(0 (= arrowCel 2))
					(90 (= arrowCel 3))
					(180 (= arrowCel 1))
					(270 (= arrowCel 4))
				)
			)
		)
		(arrow cel: arrowCel)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(helper z: 0)
				(arrow init: posn: (helper x?) (helper y?) 0)
			)
		)
	)
)

(instance blinkScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 2))
			(1 (client show:) (= cycles 4))
			(2 (client hide:) (self init:))
		)
	)
)

(instance mazeBug of Actor
	(properties
		z 1000
		signal $6010
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (MousedOn self event))
				(not (== (event type?) 16384))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 575)
					(event claimed: 1)
				)
				(verbDo
					(SpeakAudio 584)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance hole of Prop
	(properties
		signal $4010
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (MousedOn self event))
				(not (== (event type?) 16384))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 576)
					(event claimed: 1)
				)
				(verbDo
					(SpeakAudio 585)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance cell of Prop
	(properties
		signal $4010
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (MousedOn self event))
				(not (== (event type?) 16384))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 577)
					(event claimed: 1)
				)
				(verbDo
					(HandsOff)
					(curRoom setScript: goHoleScript)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance door of Door
	(properties
		signal $4010
		closeDoorNumber 8898
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (MousedOn self event))
				(not (== (event type?) 16384))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 578)
					(event claimed: 1)
				)
				(verbDo
					(if (not (Btst 68))
						(HandsOff)
						(curRoom setScript: lockedDoorScript)
					else
						(HandsOff)
						(curRoom setScript: goDoorScript)
					)
					(event claimed: 1)
				)
				(verbUse
					(switch (inventory indexOf: (theIconBar curInvIcon?))
						(1
							(SpeakAudio 251)
							(event claimed: 1)
						)
						(36
							(if (Btst 68)
								(SpeakAudio 588)
							else
								(HandsOff)
								(curRoom setScript: pickLockScript)
							)
							(event claimed: 1)
						)
						(28 (event claimed: 0))
						(else 
							(SpeakAudio 589)
							(event claimed: 1)
						)
					)
				)
			)
		)
	)
)

(instance doorBack of Prop
	(properties
		view 863
		priority 9
		signal $6010
	)
)

(instance cass of Actor
	(properties
		view 864
		signal $6000
		illegalBits $0000
	)
)

(instance dink of Actor
	(properties
		x 188
		y 138
		view 873
		priority 11
		signal $4010
		cycleSpeed 2
	)
	
	(method (init)
		(super init: &rest)
		(self setLoop: 0 cel: 0)
		(dinkBlink
			init:
			hide:
			setPri: (+ (dink priority?) 1)
			setScript: blinkScript
		)
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (MousedOn self event))
				(not (== (event type?) 16384))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 579)
					(event claimed: 1)
				)
				(verbDo
					(SpeakAudio 586)
					(event claimed: 1)
				)
				(verbUse
					(switch (inventory indexOf: (theIconBar curInvIcon?))
						(34
							(dink setScript: 0)
							(curRoom setScript: giveTamboScript)
							(event claimed: 1)
						)
						(28 (event claimed: 0))
						(else 
							(SpeakAudio 590)
							(event claimed: 1)
						)
					)
				)
				(verbTalk
					(HandsOff)
					(SpeakAudio (Random 9000 9003) self)
					(if (== (dink cel?) 6)
						(dinkTalk init: setCycle: MouthSync 9001)
					)
					(event claimed: 1)
				)
			)
		)
	)
	
	(method (cue)
		(if (== (dink cel?) 6) (dinkTalk setCycle: 0 dispose:))
		(HandsOn)
		(super cue: &rest)
	)
)

(instance dinkBlink of Prop
	(properties
		view 875
		signal $6010
	)
	
	(method (doit &tmp dinkLoop dinkCel)
		(super doit:)
		(= dinkLoop (dink loop?))
		(= dinkCel (dink cel?))
		(cond 
			((!= (dink view?) 873) (self z: 1000))
			((and (not dinkLoop) (not dinkCel))
				(self
					x: (dink x?)
					y: (- (dink y?) 39) 0
					loop: 0
					cel: 0
				)
			)
			((and (not dinkLoop) (== dinkCel 6))
				(self
					x: (- (dink x?) 4)
					y: (- (dink y?) 38) 0
					loop: 0
					cel: 1
				)
			)
			((and (== dinkLoop 4) (not dinkLoop))
				(self
					x: (- (dink x?) 6)
					y: (- (dink y?) 36) 0
					loop: 1
					cel: 0
				)
			)
			((and (== dinkLoop dinkCel) (== dinkCel 4))
				(self
					x: (- (dink x?) 10)
					y: (- (dink y?) 35) 0
					loop: 1
					cel: 1
				)
			)
			((and (== dinkLoop 6) (== dinkCel 8))
				(self
					x: (- (dink x?) 4)
					y: (- (dink y?) 38) 0
					loop: 2
					cel: 0
				)
			)
			(else (self z: 1000))
		)
	)
)

(instance dinkHair of Prop
	(properties
		view 923
		priority 10
		signal $6010
	)
	
	(method (doit)
		(super doit:)
		(if (== (dink view?) 873)
			(self
				loop: (dink loop?)
				cel: (dink cel?)
				x: (dink x?)
				y: (dink y?)
				z: 0
			)
		else
			(self z: 1000)
		)
	)
)

(instance hairPin of Actor
	(properties
		view 874
		priority 11
		signal $6010
	)
	
	(method (init)
		(super init: &rest)
		(self
			setLoop: 4
			ignoreActors: 1
			posn: (+ (dink x?) 7) (- (dink y?) 46) 0
			setCycle: Forward
		)
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (MousedOn self event))
				(not (== (event type?) 16384))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 580)
					(event claimed: 1)
				)
				(verbDo
					(Load VIEW 56)
					(HandsOff)
					(curRoom setScript: getHairPinScript)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance priBlock of View
	(properties
		x 158
		y 41
		view 865
		loop 1
		priority 15
		signal $6010
	)
)

(instance room of RFeature
	(properties
		nsBottom 200
		nsRight 320
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (MousedOn self event))
				(not (== (event type?) 16384))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 581)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance helpFeat of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (& (OnControl 4 (event x?) (event y?)) $4000))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(helper init: z: 1000 setScript: helpScript)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance dinkTalk of Prop
	(properties
		x 183
		y 123
		view 1037
	)
	
	(method (init)
		(self setPri: 15 ignoreActors: 1)
		(super init: &rest)
	)
)

(instance helper of Prop
	(properties
		x 159
		y 168
		view 877
		loop 2
		priority 14
		signal $6810
	)
)

(instance arrow of View
	(properties
		view 877
		loop 2
		priority 14
		signal $6010
	)
)
