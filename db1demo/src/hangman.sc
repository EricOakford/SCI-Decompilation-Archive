;;; Sierra Script 1.0 - (do not remove this comment)
(script# 385)
(include game.sh)
(use Main)
(use Procs)
(use HandsOffScript)
(use PuzzleBar)
(use Intrface)
(use Feature)
(use Window)
(use Motion)
(use Actor)
(use System)

(public
	hangman 0
	rope 1
	nooseLoop 2
)

(local
	[local0 3]
	local3
	local4 =  {---------------------}
	local5 =  {--------------------}
	[newLetterIcon 26]
	local32
	local33
	local34 =  10
	local35
	local36
	local37 =  1
	local38
	hangmanShow
	local40
	[local41 4]
	theIconHeight
	[local46 2] = [{BRAINS}]
	[local48 10] = [17 12 1 40 10 25 1 50 12 26]
	[local58 10] = [58 85 90 87 114 114 119 122 170 170]
	[local68 26] = [{A} {B} {C} {D} {E} {F} {G} {H} {I} {J} {K} {L} {M} {N} {O} {P} {Q} {R} {S} {T} {U} {V} {W} {X} {Y} {Z}]
	[local94 26] = [15 25 35 45 55 65 75 85 95 93 93 93 93 93 93 93 93 93 93 93 93 93 50 59 68 77]
	[local120 26] = [41 41 41 41 41 41 41 41 41 56 66 76 86 96 106 116 126 136 146 156 166 176 56 65 74 83]
)
(procedure (localproc_0016 &tmp temp0 temp1 temp2 temp3 temp4 temp5)
	(= temp0 0)
	(= temp1 7)
	(= temp2 24)
	(while (< temp0 local35)
		(if (== (= temp3 (StrAt local4 temp0)) 45)
			(DrawCel 387 2 0 temp1 temp2 -1)
		else
			(DrawCel 387 2 1 temp1 temp2 -1)
			(proc5_8
				[local68 (- temp3 65)]
				34
				(+ temp1 1)
				(+ temp2 2)
			)
		)
		(++ temp0)
		(= temp1 (+ temp1 10))
	)
	(= temp4 4)
	(= temp0 0)
	(while (< temp0 local32)
		(DrawCel 387 3 temp0 [local48 temp0] [local58 temp0] -1)
		(if
		(and (== arcadeLevel 0) (< 1 temp0) (< temp0 6))
			(DrawCel
				387
				3
				(= temp4 (+ temp0 4))
				[local48 temp4]
				[local58 temp4]
				-1
			)
		)
		(if
		(and (== arcadeLevel 1) (< 3 temp0) (< temp0 6))
			(DrawCel
				387
				3
				(= temp4 (+ temp0 4))
				[local48 temp4]
				[local58 temp4]
				-1
			)
		)
		(++ temp0)
	)
	(cond 
		((== local33 local35) (= temp5 0))
		((== local32 local36) (= temp5 5))
		((>= local32 (- local36 2)) (= temp5 4))
		((> local32 local33) (= temp5 3))
		((> local33 (+ local32 1)) (= temp5 1))
		(else (= temp5 2))
	)
	(if local32 (DrawCel 387 1 temp5 17 58 -1))
	(Graph GShowBits 0 90 189 205 1)
)

(procedure (localproc_018a &tmp [temp0 500])
	(Format @temp0 &rest)
	(Print @temp0 #at -1 12)
)

(procedure (localproc_01a8)
	(= [local0 0] [local46 0])
)

(procedure (localproc_01af param1 &tmp temp0)
	(= temp0 0)
	(while (< temp0 local35)
		(if (== (StrAt local3 temp0) param1) (return 1))
		(++ temp0)
	)
	(return 0)
)

(procedure (localproc_01d7)
	(= local32 (= local33 0))
	(= local3 [local0 (- local37 1)])
	(= local35 (StrLen local3))
	(hangman enable:)
	(StrCpy local4 local5)
)

(procedure (localproc_01ff &tmp temp0 temp1 temp2 temp3)
	(= temp0 0)
	(= temp1 7)
	(= temp2 24)
	(while (< temp0 local35)
		(DrawCel 387 2 1 temp1 temp2 -1)
		(= temp3 (StrAt local4 temp0))
		(proc5_8
			[local68 (- temp3 65)]
			52
			(+ temp1 1)
			(+ temp2 2)
		)
		(Graph GShowBits 0 90 189 205 1)
		(Wait 15)
		(++ temp0)
		(= temp1 (+ temp1 10))
	)
)

(instance hangman of PuzzleBar
	(properties
		puzzleHeight 185
		bottomHeight 0
	)
	
	(method (init &tmp temp0 temp1)
		(dummy init:)
		(hangingPost init:)
		(rope init:)
		(localproc_01a8)
		(= local3 [local0 0])
		(= local35 (StrLen local3))
		(= local32 (= local33 0))
		(= theIconHeight iconHeight)
		(= local36 (- 10 (* arcadeLevel 2)))
		(= window hngmnWindow)
		(arms init: hide:)
		(nooseLoop init: hide:)
		(ropeKnot init: setLoop: 3 setCel: 1)
		(= temp1 65)
		(while (<= temp1 90)
			(= temp0 (- temp1 65))
			(self
				add:
					((= [newLetterIcon temp0] (letterIcon new:))
						value: temp1
						nsLeft: [local94 temp0]
						nsTop: [local120 temp0]
						text: [local68 temp0]
						yourself:
					)
			)
			(++ temp1)
		)
		(super init: &rest)
	)
	
	(method (show)
		(theGame
			setCursor:
				theCursor
				1
				[local94 0]
				[local120 (if local38
					(++ local37)
					(= local38 0)
					(localproc_01d7)
				)]
		)
		(super show: &rest)
		(return (if local38 (return local37) else (return 0)))
	)
	
	(method (dispatchEvent event &tmp temp0 temp1 temp2 temp3)
		(if
			(and
				(== (= temp0 (event type?)) 4)
				(or
					(and
						(>= (= temp1 (event message?)) 97)
						(<= temp1 122)
					)
					(and (>= temp1 65) (<= temp1 90))
				)
			)
			(if (and (>= temp1 97) (<= temp1 122))
				(= temp1 (+ (- temp1 97) 65))
			)
			(= temp3 [newLetterIcon (- temp1 65)])
			(hangman select: temp3 0)
		)
		(super dispatchEvent: event)
	)
	
	(method (enable &tmp temp0 temp1)
		(= temp0 0)
		(= temp1 (self at: temp0))
		(while (< temp0 size)
			(temp1 signal: (& (temp1 signal?) $fffb))
			(++ temp0)
			(= temp1 (self at: temp0))
		)
	)
	
	(method (buyClue &tmp temp0 temp1)
		(if (super buyClue: &rest)
			(= temp0 0)
			(while (!= (StrAt local4 temp0) 45)
				(++ temp0)
			)
			(= temp1 [newLetterIcon (- (StrAt local3 temp0) 65)])
			(hangman select: temp1 0)
		)
	)
	
	(method (showHelp)
		(HighPrint 385 0)
	)
	
	(method (animateOnce)
		(curRoom doit:)
		(return 1)
	)
)

(instance letterIcon of TextIcon
	(properties
		view 387
		loop 4
		cel 0
		maskView 387
		maskLoop 4
		maskCel 1
		highlightColor 13
		lowlightColor 18
	)
	
	(method (select &tmp temp0)
		(if (super select: &rest)
			(if (localproc_01af value)
				(= temp0 0)
				(while (< temp0 local35)
					(if (== (StrAt local3 temp0) value)
						(StrAt local4 temp0 value)
						(++ local33)
					)
					(++ temp0)
				)
				(localproc_0016)
			else
				(++ local32)
				(localproc_0016)
			)
			(cond 
				((== local32 local36)
					(= local38 1)
					(= local37 -1)
					(Wait 120)
					(hangman goAway:)
				)
				((== local33 local35) (localproc_01ff) (= local38 1) (hangman goAway:))
			)
			(hangman disable: self)
			(self show:)
		)
	)
	
	(method (showText param1)
		(proc5_8 text param1 (+ nsLeft 1) (+ nsTop 2) -1 999)
	)
)

(instance hngmnWindow of SysWindow
	(properties
		left 90
		bottom 189
		right 205
		back 6
		priority -1
	)
	
	(method (open)
		(super open:)
		(DrawCel
			387
			3
			0
			[local48 0]
			[local58 (DrawCel 387 0 0 0 theIconHeight -1)]
			-1
		)
		(DrawCel 387 3 0 17 58 -1)
		(localproc_0016)
	)
)

(instance rope of Prop
	(properties
		x 242
		y 76
		view 386
		loop 3
		signal $4000
	)
)

(instance hangingPost of Feature
	(properties
		x 280
		y 83
		z 123
		nsTop 5
		nsLeft 221
		nsBottom 189
		nsRight 308
		description {hanging post}
		sightAngle 90
		onMeCheck $0008
		lookStr {It looks as though a dummy is about to be hung, probably for the "crime" of being stupid.__Either that, or Dr. Brain is challenging you to a game of "Hangman".}
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2
				(if local40
					(localproc_018a
						385
						1
						[global116 0]
						[global116 1]
						[global116 2]
					)
				else
					(HighPrint lookStr)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance dummy of Actor
	(properties
		x 238
		y 84
		description {dummy}
		lookStr {This guy looks like a real dummy.__But is that any reason to hang him?__Apparently, to Dr. Brain, it is!}
		view 386
		loop 2
		priority 14
	)
	
	(method (doVerb theVerb)
		(cond 
			((!= theVerb 3) (super doVerb: theVerb &rest))
			((> (= hangmanShow (hangman show:)) 0) (self setScript: dummyLower))
			(hangmanShow (self setScript: hangDummy))
		)
	)
)

(instance arms of Actor
	(properties
		x 238
		y 86
		view 386
		loop 1
		priority 13
		signal $0010
		cycleSpeed 24
	)
)

(instance ropeKnot of Actor
	(properties
		x 242
		y 40
		view 386
		priority 14
		signal $0010
	)
)

(instance nooseLoop of Actor
	(properties
		x 242
		y 66
		view 386
		loop 3
		cel 2
		priority 15
		signal $0010
	)
)

(instance dummyLower of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ropeKnot
					setMotion: MoveTo (ropeKnot x?) (+ (ropeKnot y?) 12)
				)
				(dummy
					setMotion: MoveTo (dummy x?) (+ (dummy y?) 12) self
				)
			)
			(1
				(switch hangmanShow
					(1
						(if (< [global116 0] 1000)
							(= [global116 0] (Random 1000 9999))
						)
						(localproc_018a 385 2 [global116 0])
						(HandsOn)
						(self dispose:)
					)
					(2
						(if (< [global116 1] 1000)
							(= [global116 1] (Random 1000 9999))
						)
						(localproc_018a 385 3 [global116 1])
						(HandsOn)
						(self dispose:)
					)
					(else  (self cue:))
				)
			)
			(2
				(ropeKnot dispose:)
				(dummy x: 239 y: 121 loop: 3 setCel: 3)
				(arms show: setCycle: EndLoop self)
			)
			(3
				(Wait 60)
				(if (< [global116 2] 1000)
					(= [global116 2] (Random 1000 9999))
				)
				(HighPrint 385 4)
				(localproc_018a 385 5 [global116 2])
				(self cue:)
			)
			(4
				(nooseLoop show:)
				(dummy dispose:)
				(Bset 6)
				(arms dispose:)
				(= local40 1)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance hangDummy of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(HandsOff)
				(dummy loop: 0 cycleSpeed: 18 setCycle: EndLoop self)
			)
			(1
				(HighPrint 385 6)
				(self cue:)
			)
			(2
				(rope hide:)
				(ropeKnot hide:)
				(dummy loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(3 (= cycles 10))
			(4
				(dummy x: 238 y: 84 setCycle: BegLoop self)
			)
			(5
				(rope show:)
				(ropeKnot y: 40 show:)
				(localproc_01a8)
				(= local37 1)
				(localproc_01d7)
				(= local38 0)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)
