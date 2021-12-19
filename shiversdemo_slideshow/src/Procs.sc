;;; Sierra Script 1.0 - (do not remove this comment)
(script# 951)
(include sci.sh)
(use Main)

(public
	proc951_0 0
	proc951_1 1
	proc951_2 2
	proc951_3 3
	proc951_4 4
	proc951_5 5
	proc951_6 6
	proc951_7 7
	proc951_8 8
	proc951_9 9
	proc951_10 10
	proc951_11 11
	proc951_12 12
	proc951_13 13
	proc951_14 14
	proc951_15 15
	proc951_16 16
)

(procedure (proc951_0 param1 &tmp temp0 temp1)
	(= temp0 (Random 0 20))
	(repeat
		(if (>= temp0 21) (= temp0 0))
		(breakif
			(== [global118 (+ (= temp1 (* temp0 2)) 1)] 0)
		)
		(++ temp0)
	)
	(= [global118 (+ temp1 1)] param1)
	(MonoOut {%d in %d} param1 [global118 temp1])
)

(procedure (proc951_1 param1 &tmp temp0 temp1)
	(= temp1 [global160 (* 2 param1)])
	(= temp0 0)
	(switch param1
		(0
			(cond 
				((== temp1 0) (= temp0 3000))
				((proc951_11 4 25000) (= temp0 9000))
				(else
					(switch (Random 0 1)
						(0 (= temp0 9000))
						(1 (= temp0 25000))
					)
				)
			)
		)
		(1
			(switch (Random 0 2)
				(0 (= temp0 8000))
				(1 (= temp0 22000))
				(2 (= temp0 24000))
			)
		)
		(2
			(cond 
				((or (== temp1 0) global182) (= temp0 6000))
				((not (proc951_5 60)) (= temp0 6000) (proc951_3 60))
				(else
					(switch (Random 0 1)
						(0 (= temp0 6000))
						(1 (= temp0 21000))
					)
				)
			)
		)
		(3
			(if global182
				(= temp0 14000)
			else
				(switch (Random 0 1)
					(0 (= temp0 11000))
					(1 (= temp0 14000))
				)
			)
		)
		(4
			(switch (Random 0 2)
				(0 (= temp0 25000))
				(1 (= temp0 20000))
				(2 (= temp0 21000))
			)
		)
		(5
			(switch (Random 0 3)
				(0 (= temp0 7000))
				(1 (= temp0 23000))
				(2 (= temp0 24000))
				(3 (= temp0 -29536))
			)
		)
		(6
			(if global182
				(= temp0 12000)
			else
				(switch (Random 0 1)
					(0 (= temp0 12000))
					(1 (= temp0 9000))
				)
			)
		)
		(7
			(switch (Random 0 2)
				(0 (= temp0 29000))
				(1 (= temp0 32000))
				(2 (= temp0 -26536))
			)
		)
		(8
			(if global182
				(= temp0 12000)
			else
				(switch (Random 0 1)
					(0 (= temp0 12000))
					(1 (= temp0 19000))
				)
			)
		)
		(9
			(if global182
				(= temp0 17000)
			else
				(switch (Random 0 2)
					(0 (= temp0 17000))
					(1 (= temp0 -28536))
					(2 (= temp0 11000))
				)
			)
		)
	)
	(= [global160 (* 2 param1)] temp0)
)

(procedure (proc951_2 param1 &tmp temp0)
	(= global109 (+ global109 param1))
	(if (> param1 0)
		(proc951_15 (* param1 75))
	else
		(proc951_15 (* param1 150))
	)
	(cond 
		((> global109 100) (= global109 100))
		((< global109 0) (= global109 0))
	)
	(= temp0 (- 10 (/ global109 10)))
	((ScriptID 950 1) cel: temp0)
	(UpdateScreenItem (ScriptID 950 1))
	(if (== global109 0) (curRoom newRoom: 914))
)

(procedure (proc951_3 param1)
	(= [global225 (/ param1 16)]
		(|
			[global225 (/ param1 16)]
			(>> $8000 (mod param1 16))
		)
	)
)

(procedure (proc951_4 param1)
	(= [global225 (/ param1 16)]
		(&
			[global225 (/ param1 16)]
			(~ (>> $8000 (mod param1 16)))
		)
	)
)

(procedure (proc951_5 param1)
	(return
		(&
			[global225 (/ param1 16)]
			(>> $8000 (mod param1 16))
		)
	)
)

(procedure (proc951_6)
	(return
		(if (or (!= mouseX gMouseX) (!= mouseY gMouseY))
			(= gMouseX mouseX)
			(= gMouseY mouseY)
			(return 1)
		else
			(return 0)
		)
	)
)

(procedure (proc951_7 param1)
	(if (ResCheck 140 param1)
		(Load 140 param1)
	else
		(Load 141 param1)
	)
	(MonoOut {Loading %d} param1)
)

(procedure (proc951_8 param1)
	(if (ResCheck 140 param1)
		(Unload 140 param1)
	else
		(Unload 141 param1)
	)
	(MonoOut {Unloading %d} param1)
)

(procedure (proc951_9 param1 &tmp temp0)
	(cond 
		((ResCheck 140 param1) (if (!= (Load 140 param1) 0) (Lock 140 param1 1)))
		((!= (Load 141 param1) 0) (Lock 141 param1 1))
	)
	(MonoOut {Locking %d} param1)
)

(procedure (proc951_10 param1)
	(if (ResCheck 140 param1)
		(Lock 140 param1 0)
	else
		(Lock 141 param1 0)
	)
	(MonoOut {Unlocking %d} param1)
)

(procedure (proc951_11 param1 param2 &tmp temp0)
	(= temp0 (== param2 [global160 (* 2 param1)]))
	(if
		(and
			(== param1 3)
			(== param2 14000)
			[global160 (* 2 param1)]
		)
		(= temp0 1)
	)
	(if (and (== param2 25000) [global160 8])
		(if (== param1 4) (= temp0 1))
		(if (and (== param1 0) [global160 0])
			(= temp0 0)
			(= [global160 0] 9000)
		)
	)
	(return temp0)
)

(procedure (proc951_12)
	(if global105
		(global108 dispose:)
		(= global108 0)
		(= global180 0)
		(switch global105
			(220
				((ScriptID 950 10) init:)
				(= global104 (| global104 $0080))
			)
			(226
				((ScriptID 950 4) init:)
				(= global104 (| global104 $0002))
			)
			(228
				((ScriptID 950 3) init:)
				(= global104 (| global104 $0001))
			)
			(229
				((ScriptID 950 5) init:)
				(= global104 (| global104 $0004))
			)
			(221
				((ScriptID 950 12) init:)
				(= global104 (| global104 $0200))
			)
			(223
				((ScriptID 950 6) init:)
				(= global104 (| global104 $0008))
			)
			(222
				((ScriptID 950 9) init:)
				(= global104 (| global104 $0040))
			)
			(224
				((ScriptID 950 11) init:)
				(= global104 (| global104 $0100))
			)
			(225
				((ScriptID 950 7) init:)
				(= global104 (| global104 $0010))
			)
			(227
				((ScriptID 950 8) init:)
				(= global104 (| global104 $0020))
			)
		)
		(sounds play: 15015 0 90 0)
		(= global105 0)
		(normalCursor show:)
	)
)

(procedure (proc951_13 &tmp temp0 temp1)
	(if global105
		(global108 dispose:)
		(= global108 0)
		(= global180 0)
		(normalCursor show:)
		(if (> global105 219)
			(= temp1 (+ (= temp0 (+ 200 (mod global105 10))) 10))
			(proc951_0 temp0)
			(proc951_0 temp1)
		else
			(proc951_0 global105)
		)
		(= global105 0)
	)
)

(procedure (proc951_14 &tmp temp0 temp1 temp2 temp3)
	(= temp3 0)
	(while (< temp3 21)
		(cond 
			((== [global118 (= temp2 (* temp3 2))] 6220) (= [global118 (+ temp2 1)] 212))
			((== [global118 temp2] 19220) (= [global118 (+ temp2 1)] 202))
			(else (= [global118 (+ temp2 1)] 0))
		)
		(++ temp3)
	)
	(= temp3 0)
	(while (< temp3 50)
		(proc951_4 temp3)
		(++ temp3)
	)
	(= temp3 0)
	(while (<= temp3 178)
		(cond 
			((< temp3 20) (= [global346 temp3] 2500))
			((< temp3 66) (= [global346 temp3] 250))
			((< temp3 80) (= [global346 temp3] 300))
			((< temp3 130) (= [global346 temp3] 350))
			((< temp3 140) (= [global346 temp3] 625))
			((< temp3 143) (= [global346 temp3] 1000))
			((< temp3 160) (= [global346 temp3] 1500))
			((< temp3 167) (= [global346 temp3] 2750))
			((< temp3 170) (= [global346 temp3] 6500))
			((< temp3 177) (= [global346 temp3] 300))
		)
		(++ temp3)
	)
	(= temp0 200)
	(while (<= temp0 219)
		(if (and (!= temp0 212) (!= temp0 202))
			(proc951_0 temp0)
		)
		(++ temp0)
	)
	(= temp1 0)
	(while (<= temp1 9)
		(proc951_1 temp1)
		(= [global160 (+ (* temp1 2) 1)] 0)
		(++ temp1)
	)
	(proc951_3 38)
	(if (> (theGame detailLevel:) 5)
		(proc951_4 36)
		(proc951_4 39)
	else
		(proc951_3 36)
		(proc951_3 39)
	)
	(= global109 100)
	(= global105 0)
	(= global106 0)
	(= global107 0)
	(= gExitFeature 0)
	(= global180 0)
	(= global181 0)
	(= gGGModNum 0)
	(= global104 0)
	(= score 0)
	(= gNewCast_2 0)
	(= temp0 0)
	(while (< temp0 6)
		(= [global318 temp0] (+ (* (Random 1 5) 2) 1))
		(= [global324 temp0] (Random 1 3))
		(++ temp0)
	)
)

(procedure (proc951_15 param1)
	(if (>= (= score (+ score param1)) 0)
		(= gNewCast_2 (+ gNewCast_2 (/ score 1000)))
		(= score (mod score 1000))
		(if (> gNewCast_2 999)
			(= gNewCast_2 999)
			(= score 999)
		)
	else
		(= score (- 0 score))
		(if gNewCast_2 (-- gNewCast_2) else (= score 0))
	)
	(if gNewCast_2
		(MonoOut {score:%3d%.3d} gNewCast_2 score)
	else
		(MonoOut {score:%6d} score)
	)
)

(procedure (proc951_16 param1)
	(if [global346 param1]
		(MonoOut {scored event:%d} param1)
		(proc951_15 [global346 param1])
		(= [global346 param1] 0)
	)
)
