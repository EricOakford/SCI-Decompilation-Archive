;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64896)
(include sci.sh)
(use Main)
(use String)
(use Array)
(use System)

(public
	Face 0
	proc64896_1 1
	proc64896_2 2
	GetTextWidth 3
	GetTextWidth_2 4
	MakeMessageText 5
	IsStringObject 6
	proc64896_7 7
	LockNLoad 8
	UnlockNUnload 9
	GetNumMessages 10
	proc64896_11 11
	proc64896_12 12
	proc64896_13 13
)

(procedure (Face param1 param2 param3 param4 &tmp temp0 temp1 temp2 temp3)
	(= temp3 0)
	(if (not (> argc 3))
		(= temp1 (param2 x?))
		(= temp2 (param2 y?))
		(if (== argc 3) (= temp3 param3))
	else
		(= temp1 param2)
		(= temp2 param3)
		(if (== argc 4) (= temp3 param4))
	)
	(= temp0
		(GetAngle (param1 x?) (param1 y?) temp1 temp2)
	)
	(param1 setHeading: temp0 temp3)
)

(procedure (proc64896_1 param1 param2 param3 &tmp temp0 temp1 [temp2 2])
	(if (or (< argc 2) (== param2 0))
		(= temp1 1)
	else
		(= temp1 param2)
	)
	(if (or (== argc 0) (== param1 1))
		(= temp0 100)
		(while (> temp0 0)
			(Palette 2 0 255 temp0)
			(FrameOut)
			(= temp0 (- temp0 temp1))
		)
		(Palette 2 0 255 0)
		(FrameOut)
	else
		(= temp0 0)
		(while (< temp0 100)
			(Palette 2 0 255 temp0)
			(FrameOut)
			(= temp0 (+ temp0 temp1))
		)
		(Palette 2 0 255 100)
		(FrameOut)
	)
	(if (and (>= argc 3) param3) (param3 cue:))
)

(procedure (proc64896_2 param1 param2)
	(if (== param1 param2) (return param1))
	(return (Random param1 param2))
)

(procedure (GetTextWidth param1 param2 param3 &tmp temp0 temp1)
	(if (< argc 3)
		(MonoOut {illegal call of GetTextWidth. djm gendialg.sc})
		(return 0)
	)
	(= temp0 (IntArray new: 4))
	(KText
		0
		(temp0 data?)
		(KArray 9 param1)
		param2
		param3
		1
	)
	(= temp1 (+ (temp0 at: 2) 1))
	(temp0 dispose:)
	(return temp1)
)

(procedure (GetTextWidth_2 param1 param2 param3 &tmp temp0 temp1)
	(if (< argc 3)
		(MonoOut {illegal call of GetTextWidth. djm gendialg.sc})
		(return 0)
	)
	(= temp0 (IntArray new: 4))
	(KText
		0
		(temp0 data?)
		(KArray 9 param1)
		param2
		param3
		1
	)
	(= temp1 (+ (temp0 at: 3) 1))
	(temp0 dispose:)
	(return temp1)
)

(procedure (MakeMessageText param1 param2 param3 param4 theTheCurRoomNum param6 &tmp temp0 temp1 temp2 temp3 temp4 theCurRoomNum temp6 temp7)
	(if (< argc 1) (= temp1 0) else (= temp1 param1))
	(if (< argc 2) (= temp2 0) else (= temp2 param2))
	(if (< argc 3) (= temp3 0) else (= temp3 param3))
	(if (< argc 4) (= temp4 1) else (= temp4 param4))
	(if (< argc 5)
		(= theCurRoomNum curRoomNum)
	else
		(= theCurRoomNum theTheCurRoomNum)
	)
	(if (< argc 6) (= temp0 0) else (= temp0 param6))
	(if
	(not (Message 0 theCurRoomNum temp1 temp2 temp3 temp4))
		(if (not temp0)
			(MonoOut
				{MakeMessageText: No message found m:%hu n:%d v:%d c:%d s:%d}
				theCurRoomNum
				temp1
				temp2
				temp3
				temp4
			)
		)
		(return 0)
	)
	(= temp6 (Str newWith: 4000 {}))
	(Message
		0
		theCurRoomNum
		temp1
		temp2
		temp3
		temp4
		(temp6 data?)
	)
	(return temp6)
)

(procedure (IsStringObject param1)
	(if (not param1)
		(MonoOut {error in call of IsStringObject})
		(return 0)
	)
	(return
		(if (== param1 (KArray 9 param1))
			(return 0)
		else
			(return 1)
		)
	)
)

(procedure (proc64896_7 param1)
	(if param1
		(if (IsStringObject param1)
			(param1 dispose:)
		else
			(KArray 4 param1)
		)
	)
)

(procedure (LockNLoad param1 param2 &tmp temp0 temp1)
	(= temp1 (Max 0 (- argc 1)))
	(= temp0 0)
	(while (< temp0 temp1)
		(if (not (ResCheck param1 [param2 temp0]))
			(MonoOut
				{Resource type: %d num %d not found in LockNLoad.}
				param1
				[param2 temp0]
			)
		else
			(MonoOut
				{Locking ResType: %d, num: %d}
				param1
				[param2 temp0]
			)
			(Load param1 [param2 temp0])
			(Lock param1 [param2 temp0] 1)
		)
		(++ temp0)
	)
)

(procedure (UnlockNUnload param1 param2 &tmp temp0 temp1)
	(= temp1 (Max 0 (- argc 1)))
	(= temp0 0)
	(while (< temp0 temp1)
		(if (not (ResCheck param1 [param2 temp0]))
			(MonoOut
				{Resource type: %d num %d not found in LockNLoad.}
				param1
				[param2 temp0]
			)
		else
			(MonoOut
				{Unlocking ResType: %d, num: %d}
				param1
				[param2 temp0]
			)
			(Lock param1 [param2 temp0] 0)
			(Unload param1 [param2 temp0])
		)
		(++ temp0)
	)
)

(procedure (GetNumMessages param1 param2 param3 param4 &tmp temp0)
	(if (< argc 4)
		(MonoOut {Illegal call of GetNumMessages})
	)
	(= temp0 1)
	(while (Message 0 param1 param2 param3 param4 temp0)
		(++ temp0)
	)
	(return (- temp0 1))
)

(procedure (proc64896_11 param1 param2)
	(if (== param1 param2) (return param1))
	(return (Random param1 param2))
)

(procedure (proc64896_12 param1 &tmp cueesSize temp1 temp2)
	(if (or (not cuees) (not (cuees size:))) (return))
	(= cueesSize (cuees size:))
	(= temp1 0)
	(while (< temp1 cueesSize)
		(if
			(and
				((= temp2 (cuees at: temp1)) respondsTo: #cuee)
				(== (temp2 cuee?) param1)
			)
			(cuees delete: temp2)
			(temp2 dispose:)
			(-- temp1)
			(-- cueesSize)
			(if (not (cuees size:)) (cuees dispose:) (= cuees 0))
		)
		(++ temp1)
	)
)

(procedure (proc64896_13)
	(if (or (not cuees) (not (cuees size:))) (return))
	(cuees dispose:)
	(= cuees 0)
)
