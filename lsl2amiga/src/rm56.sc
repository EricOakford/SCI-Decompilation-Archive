;;; Sierra Script 1.0 - (do not remove this comment)
(script# 56)
(include sci.sh)
(use Main)
(use Intrface)
(use Wander)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm56 0
)

(local
	local0
	local1
	local2
	local3
	local4
)
(instance rm56 of Rm
	(properties
		picture 56
		horizon 1
	)
	
	(method (init)
		(Load rsVIEW 522)
		(super init:)
		(= local2 166)
		(= local3 109)
		(= local4 136)
		(cond 
			((> machineSpeed 60) (= local1 4))
			((> machineSpeed 40) (= local1 3))
			((> machineSpeed 20) (= local1 2))
			(else (= local1 1))
		)
		(= local0 1)
		(while (<= local0 local1)
			((Act new:) setScript: (eastLineScript new:))
			((Act new:) setScript: (westLineScript new:))
			(++ local0)
		)
		(aMind
			setLoop: 1
			setPri: 14
			setStep: 4 4
			illegalBits: 16
			init:
		)
		(HandsOff)
		(= currentStatus 8)
		(self setRegions: 500 setScript: rm56Script)
	)
	
	(method (dispose)
		(DisposeScript 970)
		(super dispose:)
	)
)

(instance rm56Script of Script
	(properties)
	
	(method (doit)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 10))
			(1 (Print 56 0) (= seconds 10))
			(2 (Print 56 1) (= seconds 3))
			(3
				(aMind posn: 175 41 setMotion: Wander 99)
				(= seconds 10)
			)
			(4
				(aMind
					ignoreControl: 16
					setMotion: MoveTo (aMind x?) -20
				)
				(Print 56 2 #draw)
				(= seconds 10)
			)
			(5
				(Print 56 3)
				(curRoom newRoom: 57)
			)
		)
	)
)

(instance eastLineScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client
					view: 522
					setLoop: 0
					setCel: 0
					setPri: 5
					setStep: 1 1
					illegalBits: 0
					ignoreHorizon:
					ignoreActors:
					posn:
						(- 204 (* local0 (/ local3 local1)))
						(+ 2 (* local0 (/ local2 local1)))
					init:
				)
				(self changeState: 1)
			)
			(1
				(client setMotion: MoveTo 204 2 self)
			)
			(2
				(client posn: 95 168)
				(self changeState: 1)
			)
		)
	)
)

(instance westLineScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client
					view: 522
					setLoop: 0
					setCel: 1
					setPri: 5
					setStep: 1 1
					illegalBits: 0
					ignoreHorizon:
					ignoreActors:
					posn:
						(+ -58 (* local0 (/ local4 local1)))
						(+ 2 (* local0 (/ local2 local1)))
					init:
				)
				(self changeState: 1)
			)
			(1
				(client setMotion: MoveTo -58 2 self)
			)
			(2
				(client posn: 78 168)
				(self changeState: 1)
			)
		)
	)
)

(instance aMind of Act
	(properties
		y 1041
		x 175
		view 522
		signal $4000
	)
)
