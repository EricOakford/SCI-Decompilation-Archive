;;; Sierra Script 1.0 - (do not remove this comment)
(script# ANM_CAST)
(include game.sh)
(use Main)
(use Actor)
(use System)


(class ScrollBar of View
	(properties
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
	
	(method (handleEvent event &tmp temp0 newEvent temp2)
		(if
			(and
				(& (event type?) mouseDown)
				(not (event modifiers?))
			)
			(event localize: (ourCast plane?))
			(cond 
				((thumb onMe: event) (self select: 1) (event claimed: 1))
				((and lowIcon (lowIcon onMe: event))
					(lowIcon cel: (= temp0 1))
					(UpdateScreenItem lowIcon)
					(FrameOut)
					(= scratch gameTime)
					(= temp2 1)
					(while (!= ((= newEvent (Event new:)) type?) 2)
						(cond 
							((lowIcon onMe: event)
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
					(event claimed: 1)
				)
				((and highIcon (highIcon onMe: event))
					(highIcon cel: (= temp0 1))
					(UpdateScreenItem highIcon)
					(FrameOut)
					(= scratch gameTime)
					(= temp2 1)
					(while (!= ((= newEvent (Event new:)) type?) 2)
						(cond 
							((highIcon onMe: event)
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
					(event claimed: 1)
				)
				((self onMe: event) (self select: 0) (event claimed: 1))
			)
			(event localize: (cast plane?))
		)
		(event claimed?)
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
