;;; Sierra Script 1.0 - (do not remove this comment)
(script# 386)
(include game.sh)
(use Main)
(use Intrface)
(use subMarine)
(use SolvePuzzle)
(use User)
(use Actor)
(use System)

(public
	computerOn 0
)

(local
	[inputBuf 8]
	inputSpecs = [
		{} {DESTROY} {COONTZ} {DEGREE} {COURSE} {SHIP}
		{LATITUDE} {RUSSIAN} {REDWOOD} {4100} {TACTIC}
		{WAR} {STEER} {DIRECTLY} {ARIZONA} {OCEAN}
		{} {destroy} {coontz} {degree} {course} {ship}
		{latitude} {russian} {redwood} {4100} {tactic}
		{war} {steer} {directly} {arizona} {ocean}]
	[local40 12]
	local52
	local53
	local54
	saveBits
	saveBits2
	saveBits3
	saveBits4
	saveBits5
	saveBits6
	saveBits7
	saveBits8
	saveBits9
)
(procedure (ComputerDisplay theX theY theColor &tmp [str 30])
	(Format @str &rest)
	(Display @str
		p_at theX theY
		p_color theColor
		p_mode teJustLeft
		p_width 115
		p_font 30
		p_save
	)
)

(procedure (localproc_0527 &tmp i)
	(for ((= i 0)) (< i 9) ((++ i))
		(if [saveBits i]
			(Display 386 2 p_restore [saveBits i])
			(= [saveBits i] 0)
		)
	)
)

(procedure (localproc_0c24 &tmp i)
	(Format @inputBuf 386 2)
	(return
		(if (GetInput @inputBuf 15)
			(= i 0)
			(for ((= i 0)) (< i 12) ((+= i 2))
				(if (and [local40 i] (localproc_0cb2 [local40 i]))
					(= local52 i)
					(return 1)
				)
			)
			(return 0)
		else
			(return 0)
		)
	)
)

(procedure (localproc_0c7b)
	(Format @inputBuf 386 2)
	(return
		(if (GetInput @inputBuf 15)
			(if (localproc_0cb2 [local40 (+ local52 1)]) (return 1))
			(return 0)
		else
			(return 0)
		)
	)
)

(procedure (localproc_0cb2 param1)
	(return
		(cond 
			((== (StrCmp @inputBuf [inputSpecs (+ param1 0)]) 0) (return 1))
			((== (StrCmp @inputBuf [inputSpecs (+ param1 16)]) 0) (return 1))
			(else (return 0))
		)
	)
)

(procedure (Affirmative &tmp i)
	(Format @inputBuf 386 2)
	(return
		(if (GetInput @inputBuf 2)
			(cond 
				((== (StrCmp @inputBuf {Y}) 0) (return 1))
				((== (StrCmp @inputBuf {y}) 0) (return 1))
				(else (return 0))
			)
		else
			(return 0)
		)
	)
)

(instance computerOn of Script
	
	(method (init)
		(super init: &rest)
		(if (== (keyDownHandler indexOf: self) -1)
			(keyDownHandler addToFront: self)
		)
	)
	
	(method (dispose)
		(keyDownHandler delete: self)
		(= start 0)
		(HandsOn)
		(terminal dispose:)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(User canControl: FALSE)
				(= [local40 0] (>> (subMarine msg12?) $000c))
				(= [local40 1] (& (>> (subMarine msg12?) $0008) $000f))
				(= [local40 2] (& (>> (subMarine msg12?) $0004) $000f))
				(= [local40 3] (& (subMarine msg12?) $000f))
				(= [local40 4] (>> (subMarine msg34?) $000c))
				(= [local40 5] (& (>> (subMarine msg34?) $0008) $000f))
				(= [local40 6] (& (>> (subMarine msg34?) $0004) $000f))
				(= [local40 7] (& (subMarine msg34?) $000f))
				(= [local40 8] (>> (subMarine msg56?) $000c))
				(= [local40 9] (& (>> (subMarine msg56?) $0008) $000f))
				(= [local40 10]
					(& (>> (subMarine msg56?) $0004) $000f)
				)
				(= [local40 11] (& (subMarine msg56?) $000f))
				(ego loop: 3)
				(= seconds 2)
			)
			(1
				(terminal init: setPri: 12)
				(= seconds 1)
			)
			(2
				(= register
					(Display 386 1
						p_at 15 10
						p_color vLCYAN
						p_mode teJustLeft
						p_width 115
						p_font 30
						p_save
					)
				)
				(= seconds 4)
			)
			(3
				(Display 386 2 p_restore register)
				(= register
					(Display 386 3
						p_at 15 10
						p_color vWHITE
						p_mode teJustLeft
						p_width 115
						p_font 30
						p_save
					)
				)
				(if (not (localproc_0c24)) (++ local53) (++ local54))
				(= cycles 15)
			)
			(4
				(Display 386 2 p_restore register)
				(= register
					(Display 386 4
						p_at 15 10
						p_color vYELLOW
						p_mode teJustLeft
						p_width 115
						p_font 30
						p_save
					)
				)
				(if (not (localproc_0c7b local52))
					(++ local53)
					(++ local54)
				)
				(= cycles 15)
			)
			(5
				(Display 386 2 p_restore register)
				(Print 386 5)
				(= cycles 2)
			)
			(6
				(= register
					(Display 386 6
						p_at 15 10
						p_color vGREEN
						p_mode teJustLeft
						p_width 115
						p_font 30
						p_save
					)
				)
				(Print 386 7)
				(= seconds 2)
			)
			(7
				(Display 386 2 p_restore register)
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
					(switch (>> local52 $0001)
						(0
							(SolvePuzzle subMarine 407 1 3)
							(self setScript: MSG1Script self)
						)
						(1
							(SolvePuzzle subMarine 407 2 3)
							(self setScript: MSG2Script self)
						)
						(2
							(SolvePuzzle subMarine 407 4 3)
							(self setScript: MSG3Script self)
						)
						(3
							(SolvePuzzle subMarine 407 8 3)
							(self setScript: MSG4Script self)
						)
						(4
							(SolvePuzzle subMarine 407 16 3)
							(self setScript: MSG5Script self)
						)
						(5
							(SolvePuzzle subMarine 407 32 3)
							(self setScript: MSG6Script self)
						)
					)
				)
			)
			(8
				(= register
					(Display 386 8
						p_at 15 10
						p_color vGREEN
						p_mode teJustLeft
						p_width 115
						p_font 30
						p_save
					)
				)
				(= start 3)
				(if (Affirmative)
					(self init:)
				else
					(= cycles 2)
				)
			)
			(9
				(Display 386 2 p_restore register)
				(= seconds 2)
			)
			(10
				(self dispose:)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(and
					(== (event message?) ENTER)
					(== (event type?) keyDown)
					script
				)
				(script cue:)
			)
			((Said 'turn<off/computer')
				(if (OneOf state 2 3 4 6)
					(self changeState: 10)
				else
					(Print 386 0)
				)
			)
		)
	)
)

(instance MSG1Script of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= saveBits (ComputerDisplay 15 10 vLMAGENTA 386 9))
				(= saveBits2 (ComputerDisplay 15 16 vLMAGENTA 386 10))
				(= saveBits3 (ComputerDisplay 15 22 vLMAGENTA 386 11))
				(= saveBits4 (ComputerDisplay 20 28 vLMAGENTA 386 12))
				(= saveBits5 (ComputerDisplay 15 34 vLMAGENTA 386 13))
				(= saveBits6 (ComputerDisplay 20 40 vLMAGENTA 386 14))
				(= saveBits7 (ComputerDisplay 15 46 vLMAGENTA 386 15))
				(= saveBits8 (ComputerDisplay 20 52 vLMAGENTA 386 16))
			)
			(1
				(localproc_0527)
				(self dispose:)
			)
		)
	)
)

(instance MSG2Script of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(= saveBits (ComputerDisplay 15 10 vLGREEN 386 17))
				(= saveBits2 (ComputerDisplay 15 16 vLGREEN 386 18))
				(= saveBits3 (ComputerDisplay 20 22 vLGREEN 386 19))
				(= saveBits4 (ComputerDisplay 15 28 vLGREEN 386 20))
				(= saveBits5 (ComputerDisplay 20 34 vLGREEN 386 21))
			)
			(1
				(localproc_0527)
				(self dispose:)
			)
		)
	)
)

(instance MSG3Script of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(= saveBits (ComputerDisplay 15 10 vLMAGENTA 386 9))
				(= saveBits2 (ComputerDisplay 16 16 vLMAGENTA 386 22))
				(= saveBits3 (ComputerDisplay 20 22 vLMAGENTA 386 23))
				(= saveBits4 (ComputerDisplay 20 28 vLMAGENTA 386 24))
			)
			(1
				(localproc_0527)
				(self dispose:)
			)
		)
	)
)

(instance MSG4Script of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= saveBits (ComputerDisplay 15 10 vLGREEN 386 25))
				(= saveBits2 (ComputerDisplay 20 16 vLGREEN 386 26))
				(= saveBits3 (ComputerDisplay 15 22 vLGREEN 386 27))
				(= saveBits4 (ComputerDisplay 20 28 vLGREEN 386 28))
			)
			(1
				(localproc_0527)
				(self dispose:)
			)
		)
	)
)

(instance MSG5Script of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= saveBits (ComputerDisplay 15 10 vLMAGENTA 386 29))
				(= saveBits2 (ComputerDisplay 15 16 vLMAGENTA 386 30))
				(= saveBits3 (ComputerDisplay 20 22 vLMAGENTA 386 31))
				(= saveBits4 (ComputerDisplay 15 28 vLMAGENTA 386 32))
				(= saveBits5 (ComputerDisplay 15 34 vLMAGENTA 386 33))
				(= saveBits6 (ComputerDisplay 20 40 vLMAGENTA 386 34))
				(= saveBits7 (ComputerDisplay 20 46 vLMAGENTA 386 35))
				(= saveBits8 (ComputerDisplay 15 52 vLMAGENTA 386 36))
				(= saveBits9 (ComputerDisplay 20 58 vLMAGENTA 386 37))
			)
			(1
				(localproc_0527)
				(self dispose:)
			)
		)
	)
)

(instance MSG6Script of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(= saveBits (ComputerDisplay 15 10 vLGREEN 386 38))
				(= saveBits2 (ComputerDisplay 15 16 vLGREEN 386 39))
				(= saveBits3 (ComputerDisplay 20 22 vLGREEN 386 40))
				(= saveBits4 (ComputerDisplay 15 28 vLGREEN 386 41))
				(= saveBits5 (ComputerDisplay 20 34 vLGREEN 386 42))
			)
			(1
				(localproc_0527)
				(self dispose:)
			)
		)
	)
)

(instance MSG7Script of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= saveBits (ComputerDisplay 15 10 vLRED 386 43))
				(= saveBits2 (ComputerDisplay 15 16 vLRED 386 44))
				(= saveBits3 (ComputerDisplay 15 22 vLRED 386 45))
				(= saveBits4 (ComputerDisplay 20 28 vLRED 386 46))
			)
			(1
				(localproc_0527)
				(self dispose:)
			)
		)
	)
)

(instance MSG8Script of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= saveBits (ComputerDisplay 15 10 vLRED 386 47))
				(= saveBits2 (ComputerDisplay 20 16 vLRED 386 48))
				(= saveBits3 (ComputerDisplay 15 22 vLRED 386 45))
				(= saveBits4 (ComputerDisplay 20 28 vLRED 386 49))
			)
			(1
				(localproc_0527)
				(self dispose:)
			)
		)
	)
)

(instance MSG9Script of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= saveBits (ComputerDisplay 15 10 vLRED 386 50))
				(= saveBits2 (ComputerDisplay 20 16 vLRED 386 51))
				(= saveBits3 (ComputerDisplay 20 22 vLRED 386 52))
				(= saveBits4 (ComputerDisplay 15 28 vLRED 386 53))
				(= saveBits5 (ComputerDisplay 20 34 vLRED 386 54))
				(= saveBits6 (ComputerDisplay 20 40 vLRED 386 55))
				(= saveBits7 (ComputerDisplay 20 46 vLRED 386 56))
			)
			(1
				(localproc_0527)
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
