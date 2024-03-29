;;; Sierra Script 1.0 - (do not remove this comment)
(script# 603)
(include game.sh)
(use Main)
(use Motion)
(use Actor)
(use System)

(public
	proc603_0 0
	proc603_1 1
	proc603_2 2
	proc603_3 3
	proc603_4 4
	AddMazePiece 5
	proc603_6 6
	proc603_7 7
	proc603_8 8
	proc603_9 9
	proc603_10 10
	proc603_11 11
	proc603_12 12
	proc603_13 13
	proc603_14 14
	proc603_15 15
	proc603_16 16
	proc603_17 17
	proc603_18 18
	proc603_19 19
	InitMazePiece 20
	dropInScript 21
)

(local
	local0
	local1 = [1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 0 1 0 1 1 0 1 1 1 1 1 1 1 1 0 0 1 0 0 0 0 1 1 1 1 1 1 1 0 0 1 0 1 1 0 1 1 1 1 1 1 1 1 0 1 0 0 1 0 0 0 1 1 1 1 1 1 1 0 0 0 1 0 0 1 1 1 1 1 1 1 1 1 1 1 0 0 0 1 0 0 1 1 1 1 1 1 1 0 1 0 1 0 0 0 1 1 1 1 1 1 1 1 0 0 0 0 0 1 0 0 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1]
)
(procedure (proc603_0 param1)
	(|= local0 param1)
)

(procedure (proc603_1 index)
	(return (& local0 index))
)

(procedure (proc603_2 param1 param2)
	(return (+ param1 param2 (* param2 14)))
)

(procedure (proc603_3 param1 param2 param3 param4)
	(return
		(+
			global384
			(switch global383
				(0 param1)
				(90 param2)
				(180 param3)
				(270 param4)
			)
		)
	)
)

(procedure (proc603_4 param1 param2 param3 param4)
	(return
		(+
			global385
			(switch global383
				(0 param1)
				(90 param2)
				(180 param3)
				(270 param4)
			)
		)
	)
)

(procedure (AddMazePiece theView theLoop theCel theX theY thePri &tmp obj)
	((= obj (mazePiece new:))
		view: theView
		loop: theLoop
		cel: theCel
		x: theX
		y: theY
		priority: thePri
		init:
	)
	(addToPics add: obj doit:)
)

(procedure (proc603_6)
	(if
		(==
			[local1 (proc603_2 (proc603_3 -1 0 1 0) (proc603_4 0 -1 0 1))]
			1
		)
		(AddMazePiece 877 0 0 99 147 9)
		(ego observeControl: cBLUE)
		(proc603_0 1)
	else
		(AddMazePiece 877 0 4 101 128 9)
		(AddMazePiece 877 0 6 98 52 9)
	)
)

(procedure (proc603_7)
	(cond 
		((and (proc603_1 64) (proc603_1 1)) (return))
		(
			(==
				[local1 (proc603_2 (proc603_3 -1 1 1 -1) (proc603_4 -1 -1 1 1))]
				1
			)
			(if (not (proc603_1 1)) (AddMazePiece 877 0 0 99 137 8))
			(AddMazePiece 877 0 0 129 126 8)
			(proc603_0 2)
		)
		(else
			(AddMazePiece 877 0 4 130 115 8)
			(AddMazePiece 877 0 6 124 57 8)
		)
	)
)

(procedure (proc603_8)
	(cond 
		(
			(or
				(and (proc603_1 64) (proc603_1 1))
				(and (proc603_1 64) (proc603_1 2))
				(and (proc603_1 128) (proc603_1 2))
			)
			(return)
		)
		(
			(==
				[local1 (proc603_2 (proc603_3 -1 2 1 -2) (proc603_4 -2 -1 2 1))]
				1
			)
			(if (not (proc603_1 2))
				(AddMazePiece 877 1 0 67 114 6)
				(AddMazePiece 877 0 0 128 119 6)
			)
			(AddMazePiece 877 0 1 139 115 6)
			(proc603_0 4)
		)
		(else
			(AddMazePiece 877 0 5 141 109 7)
			(AddMazePiece 877 0 9 140 60 6)
		)
	)
)

(procedure (proc603_9)
	(cond 
		(
			(or
				(and (proc603_1 64) (proc603_1 1))
				(and (proc603_1 64) (proc603_1 2))
				(and (proc603_1 64) (proc603_1 4))
				(and (proc603_1 128) (proc603_1 2))
				(and (proc603_1 128) (proc603_1 4))
			)
			(return)
		)
		(
			(==
				[local1 (proc603_2 (proc603_3 -1 3 1 -3) (proc603_4 -3 -1 3 1))]
				1
			)
			(if (or (proc603_1 128) (proc603_1 256))
				(AddMazePiece 878 3 0 100 109 5)
			else
				(AddMazePiece 877 0 2 147 120 5)
				(AddMazePiece 877 0 3 138 122 5)
			)
		)
		(else
			(AddMazePiece 877 0 8 146 107 6)
			(AddMazePiece 877 0 9 148 62 6)
		)
	)
)

(procedure (proc603_10)
	(if
		(==
			[local1 (proc603_2 (proc603_3 1 0 -1 0) (proc603_4 0 1 0 -1))]
			1
		)
		(AddMazePiece 877 1 0 223 147 9)
		(ego observeControl: 64)
		(proc603_0 8)
	else
		(AddMazePiece 877 1 4 221 128 9)
		(AddMazePiece 877 1 6 221 52 9)
	)
)

(procedure (proc603_11)
	(cond 
		((and (proc603_1 64) (proc603_1 8)) (return))
		(
			(==
				[local1 (proc603_2 (proc603_3 1 1 -1 -1) (proc603_4 -1 1 1 -1))]
				1
			)
			(if (not (proc603_1 8)) (AddMazePiece 877 1 0 223 127 8))
			(AddMazePiece 877 1 0 191 131 8)
			(proc603_0 16)
		)
		(else
			(AddMazePiece 877 1 4 192 115 8)
			(AddMazePiece 877 1 6 194 57 8)
		)
	)
)

(procedure (proc603_12)
	(cond 
		(
			(or
				(and (proc603_1 64) (proc603_1 8))
				(and (proc603_1 64) (proc603_1 16))
				(and (proc603_1 128) (proc603_1 16))
			)
			(return)
		)
		(
			(==
				[local1 (proc603_2 (proc603_3 1 2 -1 -2) (proc603_4 -2 1 2 -1))]
				1
			)
			(if (not (proc603_1 16))
				(AddMazePiece 877 1 0 192 115 6)
				(AddMazePiece 877 0 0 255 118 6)
			)
			(AddMazePiece 877 1 1 180 118 6)
			(proc603_0 32)
		)
		(else
			(AddMazePiece 877 1 5 179 109 7)
			(AddMazePiece 877 1 9 179 60 6)
		)
	)
)

(procedure (proc603_13)
	(cond 
		(
			(or
				(and (proc603_1 64) (proc603_1 8))
				(and (proc603_1 64) (proc603_1 16))
				(and (proc603_1 64) (proc603_1 32))
				(and (proc603_1 128) (proc603_1 16))
				(and (proc603_1 128) (proc603_1 32))
			)
			(return)
		)
		(
			(==
				[local1 (proc603_2 (proc603_3 1 3 -1 -3) (proc603_4 -3 1 3 -1))]
				1
			)
			(if (or (proc603_1 128) (proc603_1 256))
				(AddMazePiece 878 3 2 221 109 5)
			else
				(AddMazePiece 877 1 2 173 120 5)
				(AddMazePiece 877 1 3 179 122 5)
			)
		)
		(else
			(AddMazePiece 877 1 8 173 107 6)
			(AddMazePiece 877 1 9 173 62 6)
		)
	)
)

(procedure (proc603_14)
	(AddMazePiece 878 1 0 161 146 10)
	(AddMazePiece 878 0 0 162 53 10)
)

(procedure (proc603_15)
	(if
		(==
			[local1 (proc603_2 (proc603_3 0 1 0 -1) (proc603_4 -1 0 1 0))]
			1
		)
		(AddMazePiece 878 6 0 162 131 9)
		(ego observeControl: 1024)
		(proc603_0 64)
	else
		(AddMazePiece 878 1 1 160 127 9)
		(AddMazePiece 878 0 1 160 58 9)
		(ego ignoreControl: 1024)
	)
)

(procedure (proc603_16)
	(cond 
		((proc603_1 64) (return))
		(
			(==
				[local1 (proc603_2 (proc603_3 0 2 0 -2) (proc603_4 -2 0 2 0))]
				1
			)
			(AddMazePiece 878 4 1 160 115 7)
			(proc603_0 128)
		)
		(else
			(AddMazePiece 878 1 2 160 115 7)
			(AddMazePiece 878 0 2 160 60 7)
		)
	)
)

(procedure (proc603_17)
	(cond 
		((or (proc603_1 64) (proc603_1 128)) (return))
		(
			(==
				[local1 (proc603_2 (proc603_3 0 3 0 -3) (proc603_4 -3 0 3 0))]
				1
			)
			(AddMazePiece 878 3 1 160 109 6)
			(proc603_0 256)
		)
		(else
			(AddMazePiece 878 1 3 160 109 7)
			(AddMazePiece 878 0 3 161 63 7)
		)
	)
)

(procedure (proc603_18 param1 param2)
	(return [local1 (proc603_2 param1 param2)])
)

(procedure (proc603_19)
	(= local0 0)
)

(procedure (InitMazePiece)
	(mazePiece init:)
)

(instance mazePiece of PicView
	(properties
		signal (| ignrAct fixPriOn)
	)
	
	(method (handleEvent)
		(return FALSE)
	)
)

(instance dropInScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 0)
			(1
				((ScriptID 55 1) init:)
				((ego head?) hide:)
				(ego
					normal: FALSE
					view: 865
					setLoop: 0
					setCel: 0
					ignoreActors: TRUE
					illegalBits: 0
					posn: 161 76 0
					setPri: 12
					setStep: 12 12
					setCycle: 0
				)
				(= cycles 1)
			)
			(2
				(ego setMotion: MoveTo 161 145 self)
			)
			(3
				(theAudio number: 8078 loop: 1 play:)
				(ego setCycle: EndLoop self)
			)
			(4
				((ego head?) show:)
				(ego
					normal: TRUE
					view: 0
					setLoop: -1
					loop: 3
					setCycle: Walk
					setPri: 11
					setStep: 3 2
					heading: 0
				)
				((ScriptID 55 1) dispose:)
				(HandsOn)
				(= inCartoon 0)
				(self dispose:)
			)
		)
	)
)
