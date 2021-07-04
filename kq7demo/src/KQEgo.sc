;;; Sierra Script 1.0 - (do not remove this comment)
(script# KQEGO)
(include game.sh)
(use Main)
(use Procs)
(use ScaryInventory)
(use Print)
(use StopWalk)
(use Ego)


(class KQEgo of Ego
	
	(method (init)
		(self
			setHotspot: 8 10 18 5 65 71 72 83 70 51 94 57 28 27 12
		)
		(super init: &rest)
	)
	
	(method (doit)
		(super doit:)
		(= edgeHit
			(cond 
				((<= x (curRoom edgeW?)) WEST)
				((>= x (curRoom eastSide?)) EAST)
				((> y 140) SOUTH)
				((<= y (+ (curRoom horizon?) (ego yStep?))) NORTH)
				(else 0)
			)
		)
	)
	
	(method (handleEvent event)
		(return
			(if (& (event type?) direction)
				(return FALSE)
			else
				(super handleEvent: event &rest)
			)
		)
	)
	
	(method (doVerb theVerb &tmp temp0)
		(asm
			lsp      theVerb
			dup     
			ldi      18
			eq?     
			bnt      code_0144
			pushi    1
			pushi    4
			calle    Bset,  2
			pushi    1
			lofsa    {You blow the horn.}
			push    
			calle    Prints,  2
			ldi      1
			ret     
			jmp      code_0422
code_0144:
			dup     
			ldi      5
			eq?     
			bnt      code_018f
			lsg      curRoomNum
			ldi      1250
			eq?     
			bnt      code_017d
			pushi    1
			pushi    24
			calle    Btst,  2
			bnt      code_017d
			pTos     x
			ldi      250
			lt?     
			bnt      code_017d
			pushi    #setScript
			pushi    1
			pushi    2
			pushi    1250
			pushi    1
			callk    ScriptID,  4
			push    
			lag      curRoom
			send     6
			ldi      1
			ret     
			jmp      code_0422
code_017d:
			pushi    1
			lofsa    {You start to cry.}
			push    
			calle    Prints,  2
			ldi      1
			ret     
			jmp      code_0422
code_018f:
			dup     
			ldi      65
			eq?     
			bnt      code_0236
			pushi    1
			pushi    163
			calle    Btst,  2
			bnt      code_01b8
			lsg      curRoomNum
			ldi      4350
			ne?     
			bnt      code_01b8
			pushi    1
			lofsa    {The grave digger says to summon him at the deadfall when the boogeyMan is not there.}
			push    
			calle    Prints,  2
			jmp      code_0230
code_01b8:
			pushi    1
			pushi    163
			calle    Btst,  2
			bnt      code_01d6
			lsg      curRoomNum
			ldi      4350
			eq?     
			bnt      code_01d6
			pushi    #notify
			pushi    0
			lag      curRoom
			send     4
			jmp      code_0230
code_01d6:
			pushi    #blowCount
			pushi    0
			class    122
			send     4
			sat      temp0
			+at      temp0
			push    
			ldi      3
			eq?     
			bnt      code_01fe
			pushi    #setScript
			pushi    1
			pushi    2
			pushi    4001
			pushi    1
			callk    ScriptID,  4
			push    
			class    122
			send     6
			jmp      code_0225
code_01fe:
			lsg      curRoomNum
			ldi      4350
			eq?     
			bnt      code_0211
			pushi    #notify
			pushi    0
			lag      curRoom
			send     4
			jmp      code_0225
code_0211:
			pushi    #setScript
			pushi    1
			pushi    2
			pushi    4001
			pushi    4
			callk    ScriptID,  4
			push    
			class    122
			send     6
code_0225:
			pushi    #blowCount
			pushi    1
			lst      temp0
			class    122
			send     6
code_0230:
			ldi      1
			ret     
			jmp      code_0422
code_0236:
			dup     
			ldi      71
			eq?     
			bnt      code_024e
			pushi    1
			lofsa    {You take a bite out of the moon.}
			push    
			calle    Prints,  2
			ldi      1
			ret     
			jmp      code_0422
code_024e:
			dup     
			ldi      72
			eq?     
			bnt      code_02a3
			lsg      curRoomNum
			ldi      3150
			eq?     
			bnt      code_0291
			pushi    1
			pushi    210
			calle    Bset,  2
			bnt      code_027c
			pushi    #setScript
			pushi    1
			pushi    2
			pushi    3150
			pushi    1
			callk    ScriptID,  4
			push    
			lag      curRoom
			send     6
			jmp      code_029d
code_027c:
			pushi    #setScript
			pushi    1
			pushi    2
			pushi    3150
			pushi    2
			callk    ScriptID,  4
			push    
			lag      curRoom
			send     6
			jmp      code_029d
code_0291:
			pushi    1
			lofsa    {You'd better save the salve until you need it.}
			push    
			calle    Prints,  2
code_029d:
			ldi      1
			ret     
			jmp      code_0422
code_02a3:
			dup     
			ldi      83
			eq?     
			bnt      code_02c4
			pushi    1
			pushi    205
			calle    Bset,  2
			pushi    #newRoom
			pushi    1
			pushi    6350
			lag      curRoom
			send     6
			ldi      1
			ret     
			jmp      code_0422
code_02c4:
			dup     
			ldi      70
			eq?     
			bnt      code_02ee
			pushi    1
			pushi    143
			calle    Bset,  2
			pushi    #notify
			pushi    0
			lag      curRoom
			send     4
			pushi    1
			lofsa    {Now you are the woman in black.}
			push    
			calle    Prints,  2
			ldi      1
			ret     
			jmp      code_0422
code_02ee:
			dup     
			ldi      51
			eq?     
			bnt      code_0316
			pushi    1
			pushi    114
			calle    Bset,  2
			pushi    1
			lofsa    {You eat the salt... yuck.}
			push    
			calle    Prints,  2
			pushi    #put
			pushi    1
			pushi    21
			lag      ego
			send     6
			jmp      code_0422
code_0316:
			dup     
			ldi      94
			eq?     
			bnt      code_0369
			pushi    1
			pushi    218
			calle    Btst,  2
			not     
			bnt      code_035a
			pushi    3000
			lag      curRoomNum
			lt?     
			bnt      code_0337
			pprev   
			ldi      6000
			lt?     
			bt       code_033f
code_0337:
			lsg      curRoomNum
			ldi      2000
			lt?     
			bnt      code_035a
code_033f:
			pushi    1
			lofsa    {The ghost horse comes and carries Val to clouds.}
			push    
			calle    Prints,  2
			pushi    #newRoom
			pushi    1
			pushi    6200
			lag      curRoom
			send     6
			jmp      code_0422
code_035a:
			pushi    1
			lofsa    {Nothing happens.}
			push    
			calle    Prints,  2
			jmp      code_0422
code_0369:
			dup     
			ldi      28
			eq?     
			bnt      code_03ce
			pushi    #put
			pushi    1
			pushi    16
			pushi    512
			pushi    1
			pushi    2
			lag      ego
			send     12
			lsg      curRoomNum
			ldi      1500
			eq?     
			bnt      code_0422
			pushi    1
			pushi    47
			calle    Bset,  2
			pushi    #client
			pushi    1
			pushi    0
			pushi    92
			pushi    0
			pushi    148
			pushi    0
			pushi    2
			pushi    0
			pushi    7
			callk    ScriptID,  4
			send     14
			pushi    1
			lofsa    {Ah water... nectar of the gods. Oh... wrong game.}
			push    
			calle    Prints,  2
			pushi    #setReal
			pushi    2
			pushi    2
			pushi    0
			pushi    7
			callk    ScriptID,  4
			push    
			pushi    120
			pushi    2
			pushi    0
			pushi    7
			callk    ScriptID,  4
			send     8
			jmp      code_0422
code_03ce:
			dup     
			ldi      12
			eq?     
			bnt      code_03f1
			lsg      curRoom
			ldi      3250
			eq?     
			bnt      code_0422
			pushi    1
			pushi    123
			calle    Btst,  2
			bnt      code_0422
			pushi    #cue
			pushi    0
			lag      curRoom
			send     4
			jmp      code_0422
code_03f1:
			dup     
			ldi      57
			eq?     
			bnt      code_0416
			lsg      curRoom
			ldi      5300
			eq?     
			bnt      code_0422
			pushi    1
			lofsa    {You put the mask on.}
			push    
			calle    Prints,  2
			pushi    1
			pushi    129
			calle    Bset,  2
			jmp      code_0422
code_0416:
			pushi    1
			lofsa    {Need to implement this verb in kqego.sc}
			push    
			calle    Prints,  2
code_0422:
			toss    
			ret     
		)
	)
	
	(method (setScale)
		(= scaleX (= scaleY (= maxScale scaleBase)))
		(super setScale: &rest)
	)
	
	(method (setHeading h whoCares &tmp temp0 temp1 temp2 dir)
		(if (== loop 8)
			(= dir cel)
		else
			(= dir loop)
		)
		(switch dir
			(0 (= heading 90))
			(1 (= heading 270))
			(2 (= heading 180))
			(3 (= heading 0))
			(4 (= heading 135))
			(5 (= heading 225))
			(6 (= heading 45))
			(7 (= heading 315))
		)
		(= temp2 1)
		(= temp1 (- h 23))
		(= temp0 (+ h 23))
		(if (< h 0) (= h (+ h 360)))
		(if (> h 360) (= h (- h 360)))
		(if (and (<= temp1 heading) (<= heading temp0))
			(= temp2 0)
		)
		(cond 
			(temp2
				(if argc (= heading h))
				(if looper
					(looper
						doit: self heading (if (>= argc 2) whoCares else 0)
					)
				else
					(DirLoop self heading)
					(if (and (>= argc 2) whoCares) (whoCares cue: &rest))
				)
			)
			((and (>= argc 2) whoCares) (whoCares cue: &rest))
		)
		(return heading)
	)
	
	(method (get what &tmp temp0)
		(return
			(if (not (self has: what))
				(= temp0 (inventory at: what))
				(proc18_1 temp0)
				(++ global171)
			else
				0
			)
		)
	)
	
	(method (put what recipient &tmp temp0)
		(-- global171)
		(if (self has: what)
			((= temp0 (inventory at: what))
				moveTo: (if (== argc 1) -1 else recipient)
			)
			(temp0 hide:)
			(if (== temp0 curInvItem)
				(user message: 10)
				(if (user canInput:)
					(theGame setCursor: normalCursor)
				else
					((theGame oldCurs?) view: 999 loop: 0 cel: 0)
				)
				(= theExitFeature 0)
				(= curInvItem 0)
			)
			((ScriptID 18 2) doit:)
		)
	)
	
	(method (has what &tmp temp0)
		(if (= temp0 (inventory at: what))
			(temp0 ownedBy: global104)
		)
	)
	
	(method (normalize theLoop theView)
		(if (> argc 0) (ego loop: theLoop))
		(ego
			view: (if (> argc 1) theView else 800)
			edgeHit: 0
			signal: (| skipCheck setBaseRect canUpdate)
			z: 0
			setLoop: -1
			setLooper: theGrooper
			setPri: -1
			setMotion: 0
			illegalBits: 0
			ignoreActors: FALSE
			setStep: 3 2
			setCycle: StopWalk -1
			setSpeed: (theGame currentSpeed?)
		)
	)
)
