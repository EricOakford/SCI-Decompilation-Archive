;;; Sierra Script 1.0 - (do not remove this comment)
(script# 152)
(include game.sh)
(use Main)
(use brain)
(use Sq4Feature)
(use Sound)
(use Motion)
(use System)

(public
	driveCloseUp 0
)

(local
	local0
	local1
	[programState 3]
	local5 =  1
	local6
	local7
)
(procedure (localproc_004a &tmp [temp0 20])
	(asm
		lal      local0
		bnt      code_0055
		pushi    1
		push    
		callb    DoDisplay,  2
code_0055:
		lal      local1
		bnt      code_005e
		pushi    1
		push    
		callb    DoDisplay,  2
code_005e:
		pushi    5
		lea      @temp0
		push    
		pushi    152
		pushi    0
		lsl      local6
		ldi      1
		eq?     
		bnt      code_0074
		lofsa    {>}
		jmp      code_0077
code_0074:
		lofsa    {_}
code_0077:
		push    
		ldi      1
		lsli     programState
		dup     
		eq?     
		bnt      code_0085
		lofsa    {VOHAUL}
		jmp      code_0093
code_0085:
		dup     
		ldi      2
		eq?     
		bnt      code_0090
		lofsa    {ROGER JR}
		jmp      code_0093
code_0090:
		lofsa    {_}
code_0093:
		toss    
		push    
		callk    Format,  10
		pushi    10
		lea      @temp0
		push    
		pushi    64
		pushi    62
		pushi    10
		pushi    25
		lsg      myTextColor7
		pushi    26
		lsg      myLowlightColor
		pushi    67
		pushi    75
		callb    DoDisplay,  20
		sal      local0
		pushi    5
		lea      @temp0
		push    
		pushi    152
		pushi    0
		lsl      local6
		ldi      2
		eq?     
		bnt      code_00cb
		lofsa    {>}
		jmp      code_00ce
code_00cb:
		lofsa    {_}
code_00ce:
		push    
		ldi      2
		lsli     programState
		dup     
		ldi      1
		eq?     
		bnt      code_00de
		lofsa    {VOHAUL}
		jmp      code_00ec
code_00de:
		dup     
		ldi      2
		eq?     
		bnt      code_00e9
		lofsa    {ROGER JR}
		jmp      code_00ec
code_00e9:
		lofsa    {_}
code_00ec:
		toss    
		push    
		callk    Format,  10
		pushi    10
		lea      @temp0
		push    
		pushi    64
		pushi    62
		pushi    20
		pushi    25
		lsg      myTextColor7
		pushi    26
		lsg      myLowlightColor
		pushi    67
		pushi    75
		callb    DoDisplay,  20
		sal      local1
		ret     
	)
)

(instance driveCloseUp of Sq4View
	(properties
		x 76
		y 81
		view 150
		priority 13
		signal fixPriOn
		lookStr 1
	)
	
	(method (init)
		(super init:)
		(theIconBar disable: ICON_WALK ICON_TALK ICON_SMELL ICON_TASTE ICON_ITEM ICON_INVENTORY)
		(self stopUpd:)
		(driveUpBut init:)
		(beamUpBut init:)
		(driveDownBut init:)
		(beamDownBut init:)
		(upArrowBut init:)
		(downArrowBut init:)
		(driveSlot init:)
	)
	
	(method (dispose)
		(theIconBar enable:)
		(driveUpBut dispose:)
		(beamUpBut dispose:)
		(driveDownBut dispose:)
		(beamDownBut dispose:)
		(upArrowBut dispose:)
		(downArrowBut dispose:)
		(driveSlot dispose:)
		((ScriptID 150 2)
			setMotion: MoveTo 120 110 (ScriptID 150 2)
		)
		(super dispose:)
		(DisposeScript (ScriptID 152))
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(narrator modNum: 152 say: 1)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance driveUpBut of Sq4Prop
	(properties
		x 28
		y 121
		z 100
		view 150
		loop 2
		priority 12
		signal fixPriOn
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(driveCloseUp doVerb: theVerb &rest)
			)
			(V_DO
				(curRoom setScript: buttonScript 0 self)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance driveDownBut of Sq4Prop
	(properties
		x 28
		y 140
		z 100
		view 150
		loop 3
		priority 12
		signal fixPriOn
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(driveCloseUp doVerb: theVerb &rest)
			)
			(V_DO
				(curRoom setScript: buttonScript 0 self)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance beamUpBut of Sq4Prop
	(properties
		x 28
		y 159
		z 100
		view 150
		loop 4
		priority 12
		signal fixPriOn
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(driveCloseUp doVerb: theVerb &rest)
			)
			(V_DO
				(curRoom setScript: buttonScript 0 self)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance beamDownBut of Sq4Prop
	(properties
		x 28
		y 178
		z 100
		view 150
		loop 5
		priority 12
		signal fixPriOn
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(driveCloseUp doVerb: theVerb &rest)
			)
			(V_DO
				(curRoom setScript: buttonScript 0 self)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance upArrowBut of Sq4Prop
	(properties
		x 143
		y 125
		z 100
		view 150
		loop 6
		priority 12
		signal fixPriOn
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(driveCloseUp doVerb: theVerb &rest)
			)
			(V_DO
				(curRoom setScript: buttonScript 0 self)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance downArrowBut of Sq4Prop
	(properties
		x 143
		y 147
		z 100
		view 150
		loop 7
		priority 12
		signal fixPriOn
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(driveCloseUp doVerb: theVerb &rest)
			)
			(V_DO
				(curRoom setScript: buttonScript 0 self)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance driveSlot of Sq4Prop
	(properties
		x 64
		y 573
		z 500
		view 150
		loop 1
		cel 1
		priority 14
		signal fixPriOn
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(narrator modNum: 152 say: 2)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance beep of Sound
	(properties
		number 102
	)
)

(instance buttonScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(beep play:)
				(register setPri: 15)
				(= cycles 1)
			)
			(1
				(switch register
					(driveDownBut
						(if
						(== ((inventory at: 14) owner?) (ScriptID 150 2))
							(WarningPrint 152 1)
						else
							(WarningPrint 152 2)
						)
					)
					(beamDownBut
						(cond 
							(local5 (WarningPrint 152 3) (= register 0))
							((not local6) (WarningPrint 152 4) (= register 0))
							(else
								(WarningPrint 152 5)
								(= local5 [programState local6])
								(if (== local6 1)
									(= [programState 1] [programState 2])
									(= [programState 2] 0)
								else
									(= [programState 2] 0)
									(= local6 1)
								)
								(beep dispose:)
							)
						)
						(if (and register (== local5 1))
							(curRoom notify: -1)
						else
							(SolvePuzzle fWonGame 25)
						)
					)
					(driveUpBut
						(cond 
							(local7 (WarningPrint 152 6))
							(
							(== ((inventory at: iDiskette) owner?) (ScriptID 150 2))
								(if
								(not (if (== [programState 1] 2) else (== [programState 2] 2)))
									(WarningPrint 152 7)
									(if [programState 1]
										(= [programState 2] 2)
									else
										(= [programState 1] 2)
										(= local6 1)
									)
									(localproc_004a)
									(= local7 1)
								else
									(WarningPrint 152 8)
								)
							)
							(else (WarningPrint 152 2))
						)
					)
					(beamUpBut
						(switch local5
							(0 (WarningPrint 152 9))
							(1
								(WarningPrint 152 10)
								(if [programState 1]
									(= [programState 2] 1)
								else
									(= [programState 1] 1)
									(= local6 1)
								)
								(= local5 0)
								(localproc_004a)
							)
							(2
								(WarningPrint 152 11)
								(if [programState 1]
									(= [programState 2] 2)
								else
									(= [programState 1] 2)
									(= local6 1)
								)
								(= local5 0)
								(localproc_004a)
							)
						)
					)
					(upArrowBut
						(if (> local6 1) (-- local6) (localproc_004a))
					)
					(downArrowBut
						(if (and [programState 2] (< local6 2))
							(++ local6)
							(localproc_004a)
						)
					)
				)
				(= cycles 1)
			)
			(2
				(if (== register beamDownBut) (driveCloseUp dispose:))
				(if (not register) (= register beamDownBut))
				(register setPri: 12)
				(self dispose:)
			)
		)
	)
)
