;;; Sierra Script 1.0 - (do not remove this comment)
(script# 386)
(include sci.sh)
(use Main)
(use Class_255_0)
(use subMarine)
(use n828)
(use User)
(use Feature)
(use Obj)

(public
	computerOn 0
)

(local
	[local0 8]
	[local8 32] = [{} {DESTROY} {COONTZ} {DEGREE} {COURSE} {SHIP} {LATITUDE} {RUSSIAN} {REDWOOD} {4100} {TACTIC} {WAR} {STEER} {DIRECTLY} {ARIZONA} {OCEAN} {} {destroy} {coontz} {degree} {course} {ship} {latitude} {russian} {redwood} {4100} {tactic} {war} {steer} {directly} {arizona} {ocean}]
	local40
	local52
	local53
	local54
	local55
	local56
	local57
	local58
	local59
	local60
	local61
	local62
	local63
)
(procedure (localproc_000c param1 param2 param3 &tmp [temp0 30])
	(Format @temp0 &rest)
	(Display
		@temp0
		dsCOORD
		param1
		param2
		dsCOLOR
		param3
		dsALIGN
		0
		dsWIDTH
		115
		dsFONT
		30
		dsSAVEPIXELS
	)
)

(procedure (localproc_0039 &tmp temp0)
	(= temp0 0)
	(while (< temp0 9)
		(if [local55 temp0]
			(Display 386 2 108 [local55 temp0])
			(= [local55 temp0] 0)
		)
		(++ temp0)
	)
)

(procedure (localproc_0065 &tmp temp0)
	(Format @local0 386 2)
	(return
		(if (proc255_2 @local0 15)
			(= temp0 0)
			(while (< temp0 12)
				(if
				(and [local40 temp0] (localproc_00ec [local40 temp0]))
					(= local63 temp0)
					(return 1)
				)
				(= temp0 (+ temp0 2))
			)
			(return 0)
		else
			(return 0)
		)
	)
)

(procedure (localproc_00b7)
	(Format @local0 386 2)
	(return
		(if (proc255_2 @local0 15)
			(if (localproc_00ec [local40 (+ local63 1)]) (return 1))
			(return 0)
		else
			(return 0)
		)
	)
)

(procedure (localproc_00ec param1)
	(return
		(cond 
			((== (StrCmp @local0 [local8 (+ param1 0)]) 0) (return 1))
			((== (StrCmp @local0 [local8 (+ param1 16)]) 0) (return 1))
			(else (return 0))
		)
	)
)

(procedure (localproc_0124 &tmp temp0)
	(Format @local0 386 2)
	(return
		(if (proc255_2 @local0 2)
			(cond 
				((== (StrCmp @local0 {Y}) 0) (return 1))
				((== (StrCmp @local0 {y}) 0) (return 1))
				(else (return 0))
			)
		else
			(return 0)
		)
	)
)

(instance computerOn of Script
	(properties)
	
	(method (init)
		(super init: &rest)
		(if (== (gIceKeyDownHandler indexOf: self) -1)
			(gIceKeyDownHandler addToFront: self)
		)
	)
	
	(method (dispose)
		(gIceKeyDownHandler delete: self)
		(= start 0)
		(HandsOn)
		(terminal dispose:)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(User canControl: 0)
				(= local40 (>> (subMarine msg12?) $000c))
				(= local52 (& (>> (subMarine msg12?) $0008) $000f))
				(= local53 (& (>> (subMarine msg12?) $0004) $000f))
				(= local54 (& (subMarine msg12?) $000f))
				(= local55 (>> (subMarine msg34?) $000c))
				(= local56 (& (>> (subMarine msg34?) $0008) $000f))
				(= local57 (& (>> (subMarine msg34?) $0004) $000f))
				(= local58 (& (subMarine msg34?) $000f))
				(= local59 (>> (subMarine msg56?) $000c))
				(= local60 (& (>> (subMarine msg56?) $0008) $000f))
				(= local61 (& (>> (subMarine msg56?) $0004) $000f))
				(= local62 (& (subMarine msg56?) $000f))
				(gEgo loop: 3)
				(= seconds 2)
			)
			(1
				(terminal init: setPri: 12)
				(= seconds 1)
			)
			(2
				(= register
					(Display
						386
						1
						dsCOORD
						15
						10
						dsCOLOR
						11
						dsALIGN
						0
						dsWIDTH
						115
						dsFONT
						30
						dsSAVEPIXELS
					)
				)
				(= seconds 4)
			)
			(3
				(Display 386 2 108 register)
				(= register
					(Display
						386
						3
						dsCOORD
						15
						10
						dsCOLOR
						15
						dsALIGN
						0
						dsWIDTH
						115
						dsFONT
						30
						dsSAVEPIXELS
					)
				)
				(if (not (localproc_0065)) (++ local53) (++ local54))
				(= cycles 15)
			)
			(4
				(Display 386 2 108 register)
				(= register
					(Display
						386
						4
						dsCOORD
						15
						10
						dsCOLOR
						14
						dsALIGN
						0
						dsWIDTH
						115
						dsFONT
						30
						dsSAVEPIXELS
					)
				)
				(if (not (localproc_00b7 local63))
					(++ local53)
					(++ local54)
				)
				(= cycles 15)
			)
			(5
				(Display 386 2 108 register)
				(Print 386 5)
				(= cycles 2)
			)
			(6
				(= register
					(Display
						386
						6
						dsCOORD
						15
						10
						dsCOLOR
						2
						dsALIGN
						0
						dsWIDTH
						115
						dsFONT
						30
						dsSAVEPIXELS
					)
				)
				(Print 386 7)
				(= seconds 2)
			)
			(7
				(Display 386 2 108 register)
				(if local53
					(switch (mod local54 3)
						(0
							(self setScript: MSG7Script self)
						)
						(1
							(self setScript: MSG8Script self)
						)
						(2
							(self setScript: MSG9Script self)
						)
					)
					(= local53 0)
				else
					(switch (>> local63 $0001)
						(0
							(proc828_0 subMarine 407 1 3)
							(self setScript: MSG1Script self)
						)
						(1
							(proc828_0 subMarine 407 2 3)
							(self setScript: MSG2Script self)
						)
						(2
							(proc828_0 subMarine 407 4 3)
							(self setScript: MSG3Script self)
						)
						(3
							(proc828_0 subMarine 407 8 3)
							(self setScript: MSG4Script self)
						)
						(4
							(proc828_0 subMarine 407 16 3)
							(self setScript: MSG5Script self)
						)
						(5
							(proc828_0 subMarine 407 32 3)
							(self setScript: MSG6Script self)
						)
					)
				)
			)
			(8
				(= register
					(Display
						386
						8
						dsCOORD
						15
						10
						dsCOLOR
						2
						dsALIGN
						0
						dsWIDTH
						115
						dsFONT
						30
						dsSAVEPIXELS
					)
				)
				(= start 3)
				(if (localproc_0124) (self init:) else (= cycles 2))
			)
			(9
				(Display 386 2 108 register)
				(= seconds 2)
			)
			(10 (self dispose:))
		)
	)
	
	(method (handleEvent pEvent)
		(cond 
			((super handleEvent: pEvent))
			(
				(and
					(== (pEvent message?) KEY_RETURN)
					(== (pEvent type?) evKEYBOARD)
					script
				)
				(script cue:)
			)
			((Said 'turn<off/computer')
				(if (proc999_5 state 2 3 4 6)
					(self changeState: 10)
				else
					(Print 386 0)
				)
			)
		)
	)
)

(instance MSG1Script of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local55 (localproc_000c 15 10 13 386 9))
				(= local56 (localproc_000c 15 16 13 386 10))
				(= local57 (localproc_000c 15 22 13 386 11))
				(= local58 (localproc_000c 20 28 13 386 12))
				(= local59 (localproc_000c 15 34 13 386 13))
				(= local60 (localproc_000c 20 40 13 386 14))
				(= local61 (localproc_000c 15 46 13 386 15))
				(= local62 (localproc_000c 20 52 13 386 16))
			)
			(1
				(localproc_0039)
				(self dispose:)
			)
		)
	)
)

(instance MSG2Script of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local55 (localproc_000c 15 10 10 386 17))
				(= local56 (localproc_000c 15 16 10 386 18))
				(= local57 (localproc_000c 20 22 10 386 19))
				(= local58 (localproc_000c 15 28 10 386 20))
				(= local59 (localproc_000c 20 34 10 386 21))
			)
			(1
				(localproc_0039)
				(self dispose:)
			)
		)
	)
)

(instance MSG3Script of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local55 (localproc_000c 15 10 13 386 9))
				(= local56 (localproc_000c 16 16 13 386 22))
				(= local57 (localproc_000c 20 22 13 386 23))
				(= local58 (localproc_000c 20 28 13 386 24))
			)
			(1
				(localproc_0039)
				(self dispose:)
			)
		)
	)
)

(instance MSG4Script of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local55 (localproc_000c 15 10 10 386 25))
				(= local56 (localproc_000c 20 16 10 386 26))
				(= local57 (localproc_000c 15 22 10 386 27))
				(= local58 (localproc_000c 20 28 10 386 28))
			)
			(1
				(localproc_0039)
				(self dispose:)
			)
		)
	)
)

(instance MSG5Script of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local55 (localproc_000c 15 10 13 386 29))
				(= local56 (localproc_000c 15 16 13 386 30))
				(= local57 (localproc_000c 20 22 13 386 31))
				(= local58 (localproc_000c 15 28 13 386 32))
				(= local59 (localproc_000c 15 34 13 386 33))
				(= local60 (localproc_000c 20 40 13 386 34))
				(= local61 (localproc_000c 20 46 13 386 35))
				(= local62 (localproc_000c 15 52 13 386 36))
				(= local63 (localproc_000c 20 58 13 386 37))
			)
			(1
				(localproc_0039)
				(self dispose:)
			)
		)
	)
)

(instance MSG6Script of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local55 (localproc_000c 15 10 10 386 38))
				(= local56 (localproc_000c 15 16 10 386 39))
				(= local57 (localproc_000c 20 22 10 386 40))
				(= local58 (localproc_000c 15 28 10 386 41))
				(= local59 (localproc_000c 20 34 10 386 42))
			)
			(1
				(localproc_0039)
				(self dispose:)
			)
		)
	)
)

(instance MSG7Script of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local55 (localproc_000c 15 10 12 386 43))
				(= local56 (localproc_000c 15 16 12 386 44))
				(= local57 (localproc_000c 15 22 12 386 45))
				(= local58 (localproc_000c 20 28 12 386 46))
			)
			(1
				(localproc_0039)
				(self dispose:)
			)
		)
	)
)

(instance MSG8Script of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local55 (localproc_000c 15 10 12 386 47))
				(= local56 (localproc_000c 20 16 12 386 48))
				(= local57 (localproc_000c 15 22 12 386 45))
				(= local58 (localproc_000c 20 28 12 386 49))
			)
			(1
				(localproc_0039)
				(self dispose:)
			)
		)
	)
)

(instance MSG9Script of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local55 (localproc_000c 15 10 12 386 50))
				(= local56 (localproc_000c 20 16 12 386 51))
				(= local57 (localproc_000c 20 22 12 386 52))
				(= local58 (localproc_000c 15 28 12 386 53))
				(= local59 (localproc_000c 20 34 12 386 54))
				(= local60 (localproc_000c 20 40 12 386 55))
				(= local61 (localproc_000c 20 46 12 386 56))
			)
			(1
				(localproc_0039)
				(self dispose:)
			)
		)
	)
)

(instance terminal of View
	(properties
		y 79
		x 73
		view 31
		loop 4
	)
	
	(method (init)
		(super init:)
		(self stopUpd:)
	)
	
	(method (delete)
		(super delete:)
		(DisposeScript 386)
	)
)
