;;; Sierra Script 1.0 - (do not remove this comment)
(script# 9)
(include game.sh)
(use Main)
(use GControl)
(use Game)
(use User)
(use System)

(public
	notice2Room 0
)

(local
	buttonPts = [
		14 68
		120 138
		140 141
		]
	nextRoom
)
(instance notice2Room of Room
	(properties
		picture 100
		style VSHUTTER
	)
	
	(method (init)
		(super init: &rest)
		(Bclr fHideCursor)
		(Load VIEW 100)
		(self setScript: startThisRoom)
	)
	
	(method (dispose &tmp temp0)
		(startControls eachElementDo: #dispose dispose: release:)
		(User canControl: TRUE)
		(super dispose:)
	)
)

(instance startThisRoom of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theIconBar disable:)
				(= cycles 2)
			)
			(1
				(theIconBar enable:)
				(startControls init: show:)
				(self dispose:)
			)
		)
	)
	
	(method (handleEvent event)
		(event claimed: TRUE)
	)
)

(instance startControls of GameControls
	(method (init &tmp temp0)
		(self add:
			(paper1
				theObj: introCode
				selector: #doit
				yourself:
			)
			(paper2
				theObj: charCode
				selector: #doit
				yourself:
			)
			(paper3
				theObj: restoreCode
				selector: #doit
				yourself:
			)
		)
		(super init: &rest)
	)
	
	(method (show)
		(|= state IB_ACTIVE)
		(self eachElementDo: #show)
		((= curIcon (= highlightedIcon (self at: 0)))
			highlight: TRUE
		)
		(DrawCel 100 4 0 166 22 15)
		(DrawCel 100 4 1 150 75 15)
		(DrawCel 100 4 2 152 128 15)
		(theGame setCursor:
			ARROW_CURSOR TRUE (+ (curIcon nsLeft?) 75) (- (curIcon nsBottom?) 2)
		)
		(self doit: hide:)
	)
	
	(method (dispatchEvent event &tmp evtX evtY evtType evtMsg evtClaimed evtMod obj temp7)
		(if nextRoom
			(curRoom newRoom: nextRoom)
			(event dispose:)
			(return TRUE)
		)
		(= evtX (event x?))
		(= evtY (event y?))
		(= evtType (event type?))
		(= evtMsg (event message?))
		(= evtClaimed 0)
		(= evtMod (event modifiers?))
		(= obj (self firstTrue: #onMe event))
		(event dispose:)
		(cond 
			((& evtType direction)
				(switch evtMsg
					(dirE (self advance:))
					(dirW (self retreat:))
					(dirN (self retreat:))
					(dirS (self advance:))
				)
			)
			((== evtType nullEvt)
				(cond 
					((not (IsObject obj))
						(if (IsObject highlightedIcon)
							(highlightedIcon highlight: 0)
							(= highlightedIcon 0)
						)
					)
					((and obj (!= obj highlightedIcon))
						(= oldMouseY 0)
						(self highlight: obj)
					)
				)
			)
			((not (IsObject highlightedIcon)) 0)
			((== evtType mouseDown)
				(switch highlightedIcon
					(paper1 (introCode doit:))
					(paper2 (charCode doit:))
					(paper3 (restoreCode doit:))
				)
			)
			((== evtType keyDown)
				(switch evtMsg
					(ESC
						(introCode doit:)
					)
					(ENTER
						(switch highlightedIcon
							(paper1 (introCode doit:))
							(paper2 (charCode doit:))
							(paper3 (restoreCode doit:))
						)
					)
					(TAB
						(self advance:)
					)
				)
			)
		)
		(return evtClaimed)
	)
)

(instance paper1 of ControlIcon
	(properties
		view 100
		loop 2
		cel 0
		nsLeft 139
		nsTop 14
	)
	
	(method (highlight tOrF)
		(if tOrF
			(DrawCel 100 2 1 nsLeft nsTop 15)
		else
			(DrawCel 100 2 2 nsLeft nsTop 15)
		)
	)
)

(instance paper2 of ControlIcon
	(properties
		view 100
		loop 2
		cel 0
		nsLeft 140
		nsTop 68
	)
	
	(method (highlight tOrF)
		(if tOrF
			(DrawCel 100 2 1 nsLeft nsTop 15)
		else
			(DrawCel 100 2 2 nsLeft nsTop 15)
		)
	)
)

(instance paper3 of ControlIcon
	(properties
		view 100
		loop 2
		cel 0
		nsLeft 141
		nsTop 120
	)
	
	(method (highlight tOrF)
		(if tOrF
			(DrawCel 100 2 1 nsLeft nsTop 15)
		else
			(DrawCel 100 2 2 nsLeft nsTop 15)
		)
	)
)

(instance introCode of Code
	(method (doit)
		(= nextRoom 200)
		(curRoom newRoom: nextRoom)
	)
)

(instance charCode of Code
	(method (doit)
		(= nextRoom 202)
		(curRoom newRoom: nextRoom)
	)
)

(instance restoreCode of Code
	(method (doit)
		(theGame restore:)
		(DrawCel 100 4 2 152 128 15)
	)
)
