;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64905)
(include game.sh)
(use Main)
(use User)
(use Actor)
(use System)


(class ScrollBar of View
	(properties
		type 0
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
	
	(method (handleEvent event)
		(if
			(and
				(& (event type?) mouseDown)
				(not (event modifiers?))
			)
			(event localize: (ourCast plane?))
			(cond 
				((thumb onMe: event) (self select: 1) (event claimed: 1))
				((and lowIcon (lowIcon onMe: event))
					(if (lowIcon select: relVer) (self arrowUp:))
					(event claimed: 1)
				)
				((and highIcon (highIcon onMe: event))
					(if (highIcon select: relVer) (self arrowDown:))
					(event claimed: 1)
				)
				((self onMe: event) (self select: 0) (event claimed: 1))
			)
			(event localize: (cast plane?))
		)
		(event claimed?)
	)
	
	(method (select param1 &tmp newEvent scrollBarMovementSelector temp2 temp3 temp4)
		(= scrollBarMovementSelector (self movementSelector:))
		((User curEvent?) localize: (ourCast plane?) claimed: 1)
		(cond 
			((and argc param1)
				(= temp3
					(-
						(Eval (User curEvent?) scrollBarMovementSelector)
						(Eval thumb scrollBarMovementSelector)
					)
				)
				(while (!= ((= newEvent (Event new:)) type?) 2)
					(= temp4 0)
					(newEvent localize: (ourCast plane?))
					(cond 
						(
							(<
								(-
									(= temp2 (Eval newEvent scrollBarMovementSelector))
									temp3
								)
								curPosn
							)
							(= temp4
								(if freeTrack (- (- temp2 temp3) curPosn) else -1)
							)
						)
						((> (- temp2 temp3) curPosn)
							(= temp4
								(if freeTrack (- (- temp2 temp3) curPosn) else 1)
							)
						)
					)
					(if temp4
						(self move: temp4 1)
						(if (not (& signal $0200)) (self scrolled:))
					)
					(newEvent dispose:)
				)
				(if (& signal $0200) (self scrolled:))
				(newEvent dispose:)
			)
			(
				(<
					(= temp2
						(Eval (User curEvent?) scrollBarMovementSelector)
					)
					(Eval thumb scrollBarMovementSelector)
				)
				(self pageUp:)
			)
			(
			(> temp2 (Eval thumb scrollBarMovementSelector)) (self pageDown:))
		)
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
