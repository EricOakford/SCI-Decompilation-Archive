;;; Sierra Script 1.0 - (do not remove this comment)
(script# 12)
(include sci.sh)
(use Main)
(use PolyPath)
(use System)

(public
	proc12_0 0
	proc12_1 1
	proc12_2 2
	walkEgoInScr 3
)

(local
	local0
	local1
)
(procedure (proc12_0 param1 param2 &tmp temp0 temp1 curRoomNorth)
	(theGame handsOff:)
	(switch param1
		(1
			(= temp0 0)
			(= temp1 -70)
			(= curRoomNorth (curRoom north?))
		)
		(3
			(= temp0 0)
			(= temp1 70)
			(= curRoomNorth (curRoom south?))
		)
		(2
			(= temp0 20)
			(= temp1 0)
			(= curRoomNorth (curRoom east?))
		)
		(4
			(= temp0 -20)
			(= temp1 0)
			(= curRoomNorth (curRoom west?))
		)
	)
	(if (> argc 1) (localproc_028f @temp0 @temp1 param2))
	(= local0 (+ (ego x?) temp0))
	(= local1 (+ (ego y?) temp1))
	(curRoom setScript: walkEgoOutScr 0 curRoomNorth)
)

(procedure (proc12_1 param1 param2 param3 &tmp temp0 temp1 temp2)
	(= temp0 0)
	(switch prevRoomNum
		((curRoom north?)
			(ego posn: param1 -60)
		)
		((curRoom south?)
			(ego posn: param1 250)
		)
		((curRoom east?)
			(ego posn: 370 param2)
		)
		((curRoom west?)
			(ego posn: -20 param2)
		)
		(else 
			(= temp0 1)
			(ego posn: param1 param2)
		)
	)
	(if (> argc 2)
		(= temp1 (- (ego x?) param1))
		(= temp2 (- (ego y?) param2))
		(localproc_028f @temp1 @temp2 param3)
		(ego reset: posn: (+ param1 temp1) (+ param2 temp2))
	)
	(if (not temp0)
		(= local0 param1)
		(= local1 param2)
		(curRoom setScript: walkEgoInScr)
	else
		(DisposeScript 12)
	)
)

(procedure (proc12_2 param1 param2 param3 param4 &tmp temp0 temp1 temp2 temp3)
	(= temp3 0)
	(if (IsObject param2)
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
	(param1
		setHeading: temp0 (if (IsObject temp3) temp3 else 0)
	)
	(DisposeScript 12)
)

(procedure (localproc_028f param1 param2 param3 &tmp temp0 temp1 temp2)
	(= temp0 (= temp2 (Memory memPEEK param1)))
	(= temp1 (Memory memPEEK param2))
	(= temp0
		(- (CosMult param3 temp0) (SinMult param3 temp1))
	)
	(= temp1
		(+ (SinMult param3 temp2) (CosMult param3 temp1))
	)
	(Memory memPOKE param1 temp0)
	(Memory memPOKE param2 temp1)
)

(instance walkEgoInScr of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(= register 0)
		(DisposeScript 12)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego reset: setMotion: PolyPath local0 local1 self)
			)
			(1
				((ScriptID 10 0) setIt: 4096)
				(if (not script) (= cycles 1))
			)
			(2
				((ScriptID 10 0) clrIt: 4096)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance walkEgoOutScr of Script
	(properties)
	
	(method (dispose)
		(super dispose: &rest)
		(DisposeScript 12)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: 0)
				(= cycles 1)
			)
			(1
				(ego setMotion: PolyPath local0 local1 self)
			)
			(2 (curRoom newRoom: register))
		)
	)
)
