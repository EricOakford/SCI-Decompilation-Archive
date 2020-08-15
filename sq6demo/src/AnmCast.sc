;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64905)
(include sci.sh)
(use Main)
(use Actor)
(use System)


(class ScrollBar of View
	(properties
		scratch 0
		heading 0
		noun 0
		case 0
		modNum -1
		nsLeft 0
		nsTop 0
		nsRight 0
		nsBottom 0
		sightAngle 26505
		actions 0
		onMeCheck $0000
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		plane 0
		x 0
		y 0
		z 0
		scaleX 128
		scaleY 128
		maxScale 128
		scaleType 0
		priority 0
		fixPriority 0
		inLeft 0
		inTop 0
		inRight 0
		inBottom 0
		useInsetRect 0
		view -1
		loop 0
		cel 0
		bitmap 0
		yStep 2
		signal $4021
		lsLeft 0
		lsTop 0
		lsRight 0
		lsBottom 0
		brLeft 0
		brTop 0
		brRight 0
		brBottom 0
		scaleSignal $0000
		magnifier 0
		oldScaleX 128
		type $0000
		thumbView -1
		thumbLoop 0
		thumbCel 0
		thumb 0
		object 0
		selector 0
		minPosn 0
		maxPosn 0
		curPosn 0
		ourCast 0
		pageSize 0
		lowIcon 0
		highIcon 0
		relVer 1
		freeTrack 0
	)
	
	(method (init param1 theLowIcon theHighIcon)
		(= ourCast (if (and argc param1) param1 else cast))
		(super init: ourCast)
		(if (> argc 1)
			((= lowIcon theLowIcon) init: ourCast)
			(if (> argc 2)
				((= highIcon theHighIcon) init: ourCast)
			)
		)
		((= thumb (View new:))
			view: thumbView
			loop: thumbLoop
			cel: thumbCel
			init: ourCast
		)
		(cond 
			((== type 0)
				(if (not curPosn) (= curPosn y))
				(thumb posn: x curPosn)
			)
			((== type 1)
				(if (not curPosn) (= curPosn x))
				(thumb posn: curPosn y)
			)
		)
		(UpdateScreenItem thumb)
	)
	
	(method (dispose)
		(thumb dispose:)
		(super dispose: &rest)
	)
	
	(method (handleEvent pEvent &tmp temp0 newEvent temp2)
		(if
			(and
				(& (pEvent type?) evMOUSEBUTTON)
				(not (pEvent modifiers?))
			)
			(pEvent localize: (ourCast plane?))
			(cond 
				((thumb onMe: pEvent) (self select: 1) (pEvent claimed: 1))
				((and lowIcon (lowIcon onMe: pEvent))
					(lowIcon cel: (= temp0 1))
					(UpdateScreenItem lowIcon)
					(FrameOut)
					(= scratch gameTime)
					(= temp2 1)
					(while (!= ((= newEvent (Event new:)) type?) 2)
						(cond 
							((lowIcon onMe: pEvent)
								(if (not temp0)
									(lowIcon cel: (= temp0 1))
									(UpdateScreenItem lowIcon)
									(FrameOut)
								)
								(if temp2 (self arrowUp:))
								(if (< (Abs (- gameTime scratch)) 20)
									(= temp2 0)
								else
									(= temp2 1)
								)
							)
							(temp0
								(lowIcon cel: (= temp0 0))
								(UpdateScreenItem lowIcon)
								(FrameOut)
							)
						)
						(= gameTime (+ tickOffset (GetTime)))
						(newEvent dispose:)
					)
					(if (== temp0 1)
						(lowIcon cel: 0)
						(UpdateScreenItem lowIcon)
						(FrameOut)
					)
					(pEvent claimed: 1)
				)
				((and highIcon (highIcon onMe: pEvent))
					(highIcon cel: (= temp0 1))
					(UpdateScreenItem highIcon)
					(FrameOut)
					(= scratch gameTime)
					(= temp2 1)
					(while (!= ((= newEvent (Event new:)) type?) 2)
						(cond 
							((highIcon onMe: pEvent)
								(if (not temp0)
									(highIcon cel: (= temp0 1))
									(UpdateScreenItem highIcon)
									(FrameOut)
								)
								(if temp2 (self arrowDown:))
								(if (< (Abs (- gameTime scratch)) 20)
									(= temp2 0)
								else
									(= temp2 1)
								)
							)
							(temp0
								(highIcon cel: (= temp0 0))
								(UpdateScreenItem highIcon)
								(FrameOut)
							)
						)
						(= gameTime (+ tickOffset (GetTime)))
						(newEvent dispose:)
					)
					(if (== temp0 1)
						(highIcon cel: 0)
						(UpdateScreenItem highIcon)
						(FrameOut)
					)
					(pEvent claimed: 1)
				)
				((self onMe: pEvent) (self select: 0) (pEvent claimed: 1))
			)
			(pEvent localize: (cast plane?))
		)
		(pEvent claimed?)
	)
	
	(method (select &tmp [temp0 5])
		(return 0)
	)
	
	(method (move param1 param2 &tmp scrollBarMovementSelector)
		(= scrollBarMovementSelector (self movementSelector:))
		(= curPosn
			(+ param1 (Eval thumb scrollBarMovementSelector))
		)
		(cond 
			((> curPosn maxPosn) (= curPosn maxPosn))
			((< curPosn minPosn) (= curPosn minPosn))
		)
		(Eval thumb scrollBarMovementSelector curPosn)
		(if (and (> argc 1) param2)
			(UpdateScreenItem thumb)
			(FrameOut)
		)
	)
	
	(method (rePosn theCurPosn param2 &tmp scrollBarMovementSelector)
		(= scrollBarMovementSelector (self movementSelector:))
		(= curPosn theCurPosn)
		(Eval thumb scrollBarMovementSelector curPosn)
		(if (and (> argc 1) param2)
			(UpdateScreenItem thumb)
			(FrameOut)
		)
	)
	
	(method (movementSelector)
		(return
			(cond 
				((== type 0) 2)
				((== type 1) 1)
			)
		)
	)
	
	(method (pageUp)
		(self move: (- pageSize) 1 scrolled:)
	)
	
	(method (pageDown)
		(self move: pageSize 1 scrolled:)
	)
	
	(method (arrowUp)
		(self move: -1 1 scrolled:)
	)
	
	(method (arrowDown)
		(self move: 1 1 scrolled:)
	)
	
	(method (scrolled)
		(if object (Eval object selector))
	)
)
