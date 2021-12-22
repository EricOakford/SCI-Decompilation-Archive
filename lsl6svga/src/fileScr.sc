;;; Sierra Script 1.0 - (do not remove this comment)
(script# 79)
(include sci.sh)
(use Main)
(use Plane)
(use Print)
(use Sound)
(use Motion)
(use System)

(public
	Btst 0
	Bset 1
	Bclr 2
	proc79_3 3
	proc79_4 4
	proc79_5 5
	proc79_6 6
	proc79_7 7
	proc79_8 8
	proc79_9 9
	proc79_10 10
	proc79_11 11
	proc79_12 12
	fileScr 13
	proc79_14 14
)

(local
	newSound
)
(procedure (Btst param1)
	(gameFlags test: param1)
)

(procedure (Bset param1 &tmp temp0)
	(= temp0 (Btst param1))
	(gameFlags set: param1)
	(return temp0)
)

(procedure (Bclr param1 &tmp temp0)
	(= temp0 (Btst param1))
	(gameFlags clear: param1)
	(return temp0)
)

(procedure (proc79_3 param1 param2 param3 &tmp temp0 temp1 temp2 temp3)
	(= temp3 0)
	(= temp1 (param2 x?))
	(= temp2 (param2 y?))
	(if (== argc 3) (= temp3 param3))
	(= temp0
		(GetAngle (param1 x?) (param1 y?) temp1 temp2)
	)
	(param1 setHeading: temp0 (if temp3 else 0))
)

(procedure (proc79_4 param1 param2 param3 param4 param5 param6 param7 &tmp planePicture)
	(= planePicture (Plane picture?))
	(Plane picture: -2)
	(dispText
		fore: param2
		back: param3
		font: param7
		skip: -1
		modeless: 2
		posn: param4 param5
		width: param6
		addText: param1
		font: param7
		plane: Plane
		init:
	)
	(Plane picture: planePicture)
	(FrameOut)
)

(procedure (proc79_5)
	(if (dispText dialog?) ((dispText dialog?) dispose:))
)

(procedure (proc79_6 param1)
	(if (!= global205 param1)
		(= global205 param1)
		(if (not curTalkerCast)
			(talkerPlane
				addCast:
					(= curTalkerCast
						((cast new:) name: {curTalkerCast} init: yourself:)
					)
			)
		)
		(UpdateScreenItem
			((ScriptID 92 1)
				view: param1
				setPri: 200
				init: curTalkerCast
				yourself:
			)
		)
	)
)

(procedure (proc79_7)
	(= global205 0)
	(curTalkerCast dispose:)
	(= curTalkerCast 0)
)

(procedure (proc79_8 param1)
	(switch param1
		(2
			(if (!= curRoomNum 210) (proc79_6 1210))
		)
		(18 (proc79_6 1311))
		(27 (proc79_6 1311))
		(16 (proc79_6 1810))
		(20 (proc79_6 1242))
		(17 (proc79_6 1230))
		(6
			(cond 
				((== curRoomNum 370) (proc79_6 1521))
				((!= curRoomNum 520) (proc79_6 1520))
			)
		)
		(3
			(cond 
				((== curRoomNum 370) (proc79_6 1461))
				((not (curRoom inset?)) (proc79_6 1460))
			)
		)
		(12
			(if (!= curRoomNum 410) (proc79_6 1410))
		)
		(15 (proc79_6 1241))
		(9 (proc79_6 1592))
		(23 (proc79_6 1619))
		(13
			(if (!= curRoomNum 250) (proc79_6 1250))
		)
		(21
			(if (!= curRoomNum 390) (proc79_6 1390))
		)
		(10
			(cond 
				((== curRoomNum 590))
				((== curRoomNum 860) (proc79_6 1590))
				(else (proc79_6 1591))
			)
		)
		(25 (proc79_6 1146))
		(22 (proc79_6 1513))
		(11
			(cond 
				((Btst 92) (proc79_6 1431))
				((== curRoomNum 420) (proc79_6 1432))
				((!= curRoomNum 430) (proc79_6 1430))
			)
		)
		(24 (proc79_6 1144))
	)
	(= global178 (Random 30 120))
)

(procedure (proc79_9 param1)
	(gameFlags test: param1)
)

(procedure (proc79_10 param1)
	(gameFlags test: param1)
)

(procedure (proc79_11 param1 &tmp temp0)
	(= temp0 0)
	(while (<= temp0 (- argc 1))
		(= [global237 temp0] [param1 temp0])
		(cond 
			((ResCheck 140 [global237 temp0])
				(if (== global100 120)
					(Printf
						{element %d sound %d is type RES WAVE}
						temp0
						[global237 temp0]
					)
				)
				(Lock
					[global216 temp0]
					[global237 (Load
						[global216 temp0]
						[global237 (= [global216 temp0] 140)]
					)]
					1
				)
			)
			((ResCheck 141 [global237 temp0])
				(if (== global100 120)
					(Printf
						{element %d sound %d is type RES AUDIO}
						temp0
						[global237 temp0]
					)
				)
				(Lock
					[global216 temp0]
					[global237 (Load
						[global216 temp0]
						[global237 (= [global216 temp0] 141)]
					)]
					1
				)
			)
			((ResCheck 132 [global237 temp0])
				(if (== global100 120)
					(Printf
						{element %d sound %d is type RES SOUND}
						temp0
						[global237 temp0]
					)
				)
				(Load
					[global216 temp0]
					[global237 (= [global216 temp0] 132)]
				)
			)
			((== global100 120)
				(Printf
					{element %d sound %d is type ?!}
					temp0
					[global237 temp0]
				)
				(SetDebug)
			)
		)
		(++ temp0)
	)
)

(procedure (proc79_12 param1 &tmp temp0 temp1)
	(= temp1 0)
	(while (<= temp1 20)
		(= temp0 0)
		(while (<= temp0 (- argc 1))
			(if
				(and
					(> [global237 temp1] 0)
					(== [param1 temp0] [global237 temp1])
				)
				(UnLoad
					[global216 temp1]
					[global237 (cond 
						((== [global216 temp1] 140)
							(Lock [global216 temp1] [global237 temp1] 0)
							(if (== global100 120)
								(Palette 2 0 255 100)
								(Printf
									{DumpFx: dumping element %d type RES WAVE sound %d}
									temp1
									[global237 temp1]
								)
							)
						)
						((== [global216 temp1] 141)
							(Lock [global216 temp1] [global237 temp1] 0)
							(if (== global100 120)
								(Palette 2 0 255 100)
								(Printf
									{DumpFx: dumping element %d type RES AUDIO sound %d}
									temp1
									[global237 temp1]
								)
							)
						)
						(
						(and (== [global216 temp1] 132) (== global100 120))
							(Palette 2 0 255 100)
							(Printf
								{DumpFx: dumping element %d type RES SOUND sound %d}
								temp1
								[global237 temp1]
							)
						)
					)]
				)
				(= [global216 temp1] 0)
				(= [global237 temp1] 0)
				(= [param1 temp0] 0)
			)
			(++ temp0)
		)
		(++ temp1)
	)
)

(procedure (proc79_14)
	(asm
		pushi    #edgeHit
		pushi    0
		lag      ego
		send     4
		bt       code_0450
		pushi    #mover
		pushi    0
		lag      ego
		send     4
		bt       code_0450
		pushi    #size
		pushi    0
		lag      talkers
		send     4
		bt       code_0450
		pushi    #script
		pushi    0
		lag      curRoom
		send     4
		bt       code_0450
		pushi    #script
		pushi    0
		lag      ego
		send     4
		bt       code_0450
		pushi    #view
		pushi    0
		lag      ego
		send     4
		push    
		ldi      900
		ne?     
		bt       code_0450
		pushi    #controls
		pushi    0
		lag      user
		send     4
		not     
		bt       code_0450
		pushi    #isHandsOff
		pushi    0
		lag      theGame
		send     4
		bt       code_0450
		pushi    #script
		pushi    0
		lag      theGame
		send     4
		bt       code_0450
		pushi    #dialog
		pushi    0
		class    Print
		send     4
		bt       code_0450
		pushi    #input
		pushi    0
		lag      user
		send     4
		not     
		bnt      code_0450
code_0450:
		ret     
	)
)

(instance dispText of Print
	(properties
		doBorder 0
	)
	
	(method (doit)
	)
	
	(method (handleEvent)
		(return 0)
	)
)

(instance fileScr of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setHeading: 45 self)
			)
			(1 (= cycles 2))
			(2
				(ego
					view: 87
					setLoop: 0 1
					cel: 0
					cycleSpeed: 8
					setCycle: End self
				)
			)
			(3
				(Message 0 85 5 0 14 1 (global186 data?))
				(messager say: 5 4 0 0 self 85)
			)
			(4
				(if (> howFast 3) (= temp0 4) else (= temp0 2))
				(ego setLoop: 1 1 cel: 0 cycleSpeed: temp0)
				((= newSound (Sound new:)) number: 37 loop: 1)
				(= register 12)
				(= cycles 2)
			)
			(5
				(newSound play:)
				(ego setCycle: End self)
			)
			(6
				(if (-- register) (= state (- state 2)))
				(ego setCycle: Beg self)
			)
			(7
				(newSound dispose:)
				(= newSound 0)
				(ego normalize: 900 6 1 setHeading: 180 self)
			)
			(8
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)
