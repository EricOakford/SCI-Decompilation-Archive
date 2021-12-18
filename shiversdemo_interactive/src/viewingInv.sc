;;; Sierra Script 1.0 - (do not remove this comment)
(script# 992)
(include sci.sh)
(use Main)
(use Procs)
(use ShiversSound)
(use Reverse)
(use Motion)
(use Actor)

(public
	viewingInv 0
)

(local
	local0 =  2
	local1 =  2
	local2
	[local3 2]
)
(instance ambience of ShiversSound
	(properties
		number 25001
		loop -1
	)
)

(instance viewingInv of ShiversRoom
	(properties)
	
	(method (init &tmp temp0)
		(if global115 (global115 hide:))
		(gSound1 pause: 1)
		(gSound2 pause: 1)
		(gSound3 pause: 1)
		(gSound4 pause: 1)
		(gSound5 pause: 1)
		(gSound6 pause: 1)
		(global108 hide:)
		(normalCursor show:)
		(pRotatingObject init:)
		(proc951_7 25001)
		(ambience play: 0 0)
		(ambience fade: 42 1 30 0 0)
		(super init: &rest)
	)
	
	(method (dispose)
		(ambience fade: 0 1 30 1 0)
		(pRotatingObject dispose:)
		(gSound1 pause: 0)
		(gSound2 pause: 0)
		(gSound3 pause: 0)
		(gSound4 pause: 0)
		(gSound5 pause: 0)
		(gSound6 pause: 0)
		(global108 show:)
		(sounds play: 15030 0 90 0)
		(super dispose: &rest)
	)
)

(instance pRotatingObject of Prop
	(properties
		x 133
		y 71
	)
	
	(method (init)
		(self
			view: (+ (mod global105 10) 230)
			loop: (/ (mod global105 100) 10)
			cel: 0
		)
		(mouseDownHandler add: self)
		(super init: &rest)
	)
	
	(method (doit)
		(cond 
			((> (- mouseY 7) 143) (= local1 2))
			(
			(and (< -27 (- mouseX 27)) (< (- mouseX 27) 47)) (= local1 0))
			(
			(and (< 46 (- mouseX 27)) (< (- mouseX 27) 96)) (= local1 1))
			(
			(and (< 95 (- mouseX 27)) (< (- mouseX 27) 169)) (= local1 2))
			(
			(and (< 168 (- mouseX 27)) (< (- mouseX 27) 218)) (= local1 3))
			(
			(and (< 217 (- mouseX 27)) (< (- mouseX 27) 320)) (= local1 4))
		)
		(if (!= local1 local0)
			(= local0 local1)
			(switch local1
				(0
					(MonoOut {FastReverse})
					(= local2 0)
					(self setCycle: Fwd cycleSpeed: 6)
				)
				(1
					(MonoOut {SlowReverse})
					(= local2 0)
					(self setCycle: Fwd cycleSpeed: 12)
				)
				(2
					(MonoOut {Stop})
					(self setCycle: 0)
				)
				(3
					(MonoOut {SlowForward})
					(= local2 1)
					(self setCycle: Rev cycleSpeed: 12)
				)
				(4
					(MonoOut {FastForward})
					(= local2 1)
					(self setCycle: Rev cycleSpeed: 6)
				)
			)
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(super dispose: &rest)
	)
	
	(method (handleEvent event)
		(event localize: thePlane)
		(if
			(and
				(& (event type?) evMOUSEBUTTON)
				(self onMe: event)
			)
			(event claimed: 1)
			(curRoom newRoom: prevRoomNum)
			(self dispose:)
		)
		(super handleEvent: event)
	)
)
