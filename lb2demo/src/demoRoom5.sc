;;; Sierra Script 1.0 - (do not remove this comment)
(script# 5)
(include game.sh)
(use Main)
(use RandCyc)
(use PolyPath)
(use LoadMany)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	demoRoom5 0
)

(instance demoRoom5 of Room
	(properties
		picture 480
		style IRISIN
	)
	
	(method (init)
		(Load PICTURE 780)
		(LoadMany VIEW 804 1781 1782 1783)
		(ego view: 804 posn: 163 114 setStep: 2 1 init: hide:)
		(lauraV init: hide:)
		(tut init: hide:)
		(pippin init: hide:)
		(super init:)
		(self setScript: sCartoon)
	)
)

(instance sCartoon of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(DoDisplay
					{Dead things are everywhere.}
					0
					20071
					200
					182
					2
					myTextColor
					myBordColor
				)
				(= seconds 4)
			)
			(1 (ego show:) (= cycles 1))
			(2
				(ego setMotion: PolyPath 180 137 self)
			)
			(3
				(ego hide:)
				(lauraV show:)
				(tut show:)
				(pippin show:)
				(curRoom drawPic: 780 IRISIN)
				(= cycles 2)
			)
			(4
				(DoDisplay
					{Then, when she attends a Museum party that night to gather more clues, Laura's burglary investigation turns into...}
					1
					20071
					300
					10
					0
					global116
					myBordColor
				)
				(= seconds 8)
			)
			(5
				(UnLoad PICTURE 780)
				(curRoom newRoom: 6)
			)
		)
	)
)

(instance sBlinkEyes of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(lauraEyes setCycle: EndLoop self)
			)
			(1 (= seconds 3))
			(2
				(lauraEyes cel: 0)
				(self init:)
			)
		)
	)
)

(instance sPBlink of Script

	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 1))
			(1
				(pippinEyes setCycle: EndLoop self)
			)
			(2
				(pippinEyes cel: 0)
				(= seconds 2)
			)
			(3 (self init:))
		)
	)
)

(instance sPBrows of Script

	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 3))
			(1
				(pippinBrow
					loop: 4
					cel: 0
					cycleSpeed: 17
					setCycle: EndLoop self
				)
			)
			(2 (= seconds 3))
			(3
				(pippinBrow loop: 5 cel: 0 setCycle: EndLoop self)
			)
			(4 (self init:))
		)
	)
)

(instance sTBlink of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 1))
			(1 (tutEyes setCycle: EndLoop self))
			(2
				(tutEyes cel: 0)
				(= seconds 3)
			)
			(3 (self init:))
		)
	)
)

(instance sTBrow of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 2))
			(1
				(tutBrow cycleSpeed: 12 setCycle: EndLoop self)
			)
			(2 (= seconds 3))
			(3
				(tutBrow cel: 0)
				(self init:)
			)
		)
	)
)

(instance sTMust of Script

	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 1))
			(1
				(tutMust cycleSpeed: 12 setCycle: EndLoop self)
			)
			(2 (= seconds 2))
			(3
				(tutMust cel: 0)
				(self init:)
			)
		)
	)
)

(instance lauraV of View
	(properties
		x -20
		y 40
		view 1781
		loop 1
	)
	
	(method (init)
		(lauraEyes
			posn: (+ (self x?) 91) (+ (self y?) 18)
			setScript: sBlinkEyes
			init:
		)
		(super init:)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (hide)
		(lauraEyes hide:)
		(super hide:)
	)
	
	(method (show)
		(lauraEyes show:)
		(super show:)
	)
)

(instance lauraEyes of Prop
	(properties
		view 1781
		loop 2
	)
)

(instance tut of View
	(properties
		x 192
		y 20
		view 1783
		loop 1
	)
	
	(method (init)
		(tutEyes
			posn: (+ (self x?) 65) (+ (self y?) 22)
			init:
		)
		(tutBrow
			posn: (+ (self x?) 62) (+ (self y?) 20)
			init:
		)
		(tutMust
			posn: (+ (self x?) 58) (+ (self y?) 42)
			init:
		)
		(super init:)
	)
	
	(method (dispose)
		(tutEyes dispose:)
		(tutBrow dispose:)
		(tutMust dispose:)
		(super dispose:)
	)
	
	(method (hide)
		(tutEyes hide:)
		(tutBrow hide:)
		(tutMust hide:)
		(super hide:)
	)
	
	(method (show)
		(tutEyes show: setScript: sTBlink)
		(tutBrow show: setScript: sTBrow)
		(tutMust show: setScript: sTMust)
		(super show:)
	)
)

(instance tutEyes of Prop
	(properties
		view 1783
		loop 2
	)
)

(instance tutBrow of Prop
	(properties
		view 1783
		loop 4
		signal ignrAct
	)
)

(instance tutMust of Prop
	(properties
		view 1783
		loop 5
	)
)

(instance pippin of View
	(properties
		x 130
		y 32
		view 1782
		loop 1
	)
	
	(method (init)
		(pippinMouth
			posn: (+ (self x?) 34) (+ (self y?) 6)
			cycleSpeed: 10
			init:
		)
		(pippinEyes
			posn: (+ (self x?) 47) (+ (self y?) 19)
			init:
		)
		(pippinBrow
			posn: (+ (self x?) 51) (+ (self y?) 17)
			init:
		)
		(super init:)
	)
	
	(method (dispose)
		(pippinMouth dispose:)
		(pippinEyes dispose:)
		(pippinBrow dispose:)
		(super dispose:)
	)
	
	(method (hide)
		(pippinMouth hide:)
		(pippinEyes hide:)
		(pippinBrow hide:)
		(super hide:)
	)
	
	(method (show)
		(pippinMouth show: setCycle: RandCycle)
		(pippinEyes show: setScript: sPBlink)
		(pippinBrow show: setScript: sPBrows)
		(super show:)
	)
)

(instance pippinMouth of Prop
	(properties
		view 1782
	)
)

(instance pippinEyes of Prop
	(properties
		view 1782
		loop 2
	)
)

(instance pippinBrow of Prop
	(properties
		view 1782
		loop 4
		signal ignrAct
	)
)

(instance browWave of Prop
	(properties
		view 1782
		loop 5
		signal ignrAct
	)
)
