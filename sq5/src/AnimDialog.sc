;;; Sierra Script 1.0 - (do not remove this comment)
(script# ANIMDLG)
(include game.sh)
(use Main)
(use Intrface)
(use Print)
(use Talker)
(use RandCyc)
(use System)


(class AnimDialog of Dialog
	
	(method (doit def &tmp event ret done)
		(= gameTime (+ tickOffset (GetTime)))
		(= done 0)
		(self eachElementDo: #init)
		(if theItem
			(theItem select: FALSE)
		)
		(= theItem
			(if (and argc def)
				def
			else
				(self firstTrue: #checkState dActive)
			)
		)
		(if theItem
			(theItem select: TRUE)
		)
		(if (not theItem)
			(= eatTheMice eatMice)
			(= lastTicks (GetTime))
		else
			(= eatTheMice 0)
		)
		(= ret 0)
		(while (not ret)
			(cond 
				((IsObject fastCast)
					(fastCast eachElementDo: #doit)
				)
				((IsObject theDoits)
					(theDoits eachElementDo: #doit)
				)
			)
			(= gameTime (+ tickOffset (GetTime)))
			(self eachElementDo: #cycle)
			(= event ((Event new:) localize:))
			(if eatTheMice
				(-- eatTheMice)
				(if (== (event type?) mouseDown)
					(event type: 0)
				)
				(while (== lastTicks (GetTime))
				)
				(= lastTicks (GetTime))
			)
			(self eachElementDo: #perform checkHiliteCode self event)
			(= ret (self handleEvent: event))
			(event dispose:)
			(if (self check:) (break))
			(if (== ret -2)
				(= ret 0)
				(EditControl theItem 0)
				(break)
			)
			(Wait 1)
		)
		(return ret)
	)
)

(instance checkHiliteCode of Code
	
	(method (doit who dlg event)
		(if
			(and
				(& (who state?) dActive)
				(who check: event)
				(not (& (who state?) dSelected))
			)
			((dlg theItem?) select: FALSE)
			(dlg theItem: who)
			(who select: TRUE)
		)
	)
)

(class ChoiceNarrator of Narrator
	(properties
		whichSelect 0
		normal 0
		curNoun 0
		curVerb 0
		curCase 0
	)
	
	(method (display theBuf &tmp theWidth theWin temp2 temp3 temp4 temp5 printObj)
		(if normal
			(super display: theBuf &rest)
		else
			(if (> (+ x talkWidth) 318)
				(= theWidth (- 318 x))
			else
				(= theWidth talkWidth)
			)
			((= theWin (systemWindow new:))
				color: color
				back: back
			)
			(if (and (not (HaveMouse)) (!= theCursor INVIS_CURSOR))
				(= saveCursor theCursor)
				(theGame setCursor: INVIS_CURSOR)
			else
				(= saveCursor 0)
			)
			(if showTitle
				(Print addTitle: name)
			)
			((= printObj (Print new:))
				dialog: (AnimDialog new:)
				window: theWin
				posn: x y
				font: font
				width: theWidth
				addText: theBuf
				modeless: FALSE
			)
			(if (not normal)
				(= temp3
					(+
						(-
							((= temp2 (NodeValue ((printObj dialog?) last:)))
								nsBottom?
							)
							(temp2 nsTop?)
						)
						4
						y
					)
				)
				(= temp4 2)
				(while
					(= temp5
						(Message MsgSize modNum curNoun curVerb curCase temp4)
					)
					(printObj
						addColorButton: (- temp4 1) curNoun curVerb curCase temp4 x temp3 modNum
					)
					(= temp2 (NodeValue ((printObj dialog?) last:)))
					(= temp3
						(+ temp3 (- (temp2 nsBottom?) (temp2 nsTop?)) 4)
					)
					(++ temp4)
				)
				(= whichSelect (printObj init:))
				(self dispose: 1)
			else
				(printObj init:)
			)
		)
	)
)

(class ChoiceTalker of Talker
	(properties
		verb 0
		case 0
		whichSelect 0
		normal 0
		curNoun 0
		curVerb 0
		curCase 0
	)
	
	(method (show &tmp pnv port)
		(if normal
			(super show: &rest)
		else
			(= port (GetPort))
			(SetPort 0)
			(if (not underBits)
				(= underBits (Graph GSaveBits nsTop nsLeft nsBottom nsRight VMAP))
			)
			(= pnv (PicNotValid))
			(PicNotValid TRUE)
			(if bust
				(DrawCel
					(bust view?)
					(bust loop?)
					(bust cel?)
					(+ (bust nsLeft?) nsLeft)
					(+ (bust nsTop?) nsTop)
					-1
				)
			)
			(if eyes
				(DrawCel
					(eyes view?)
					(eyes loop?)
					(eyes cel?)
					(+ (eyes nsLeft?) nsLeft)
					(+ (eyes nsTop?) nsTop)
					-1
				)
			)
			(if mouth
				(DrawCel
					(mouth view?)
					(mouth loop?)
					(mouth cel?)
					(+ (mouth nsLeft?) nsLeft)
					(+ (mouth nsTop?) nsTop)
					-1
				)
			)
			(DrawCel view loop cel nsLeft nsTop -1)
			(Graph GShowBits nsTop nsLeft nsBottom nsRight VMAP)
			(SetPort port)
			(PicNotValid pnv)
		)
	)
	
	(method (say theBuf whoCares)
		(return
			(if normal
				(super say: theBuf whoCares &rest)
			else
				(if (and (> view 0) (not underBits))
					(self init:)
				)
				(if theIconBar
					(theIconBar disable:)
				)
				(if (not initialized)
					(self init:)
				)
				(= caller
					(if (and (> argc 1) whoCares) whoCares else 0)
				)
				(if (IsObject fastCast)
					(fastCast add: self)
				else
					((= fastCast (EventHandler new:))
						name: {fastCast}
						add: self
					)
				)
				(if (& msgType CD_MSG)
					(self startAudio:)
				)
				(if (& msgType TEXT_MSG)
					(self startText: theBuf)
				)
				(= ticks (+ ticks 60 gameTime))
				(return TRUE)
			)
		)
	)
	
	(method (startText theBuf &tmp strLength)
		(return
			(if normal
				(super startText: theBuf &rest)
			else
				(if (not viewInPrint)
					(self show:)
				)
				(if (not (& msgType CD_MSG))
					(= ticks (Max 240 (* 8 (= strLength (StrLen theBuf)))))
				)
				(if mouth
					(mouth setCycle: RandCycle (* 4 strLength) 0 1)
				)
				(if (and eyes (not (eyes cycler?)))
					(eyes setCycle: Blink blinkSpeed)
				)
				(if modelessDialog
					(modelessDialog dispose:)
				)
				(self display: theBuf)
				(return strLength)
			)
		)
	)
	
	(method (display theBuf &tmp theLoop theWidth temp2 theWin temp4 temp5 temp6 temp7 printObj)
		(if normal
			(super display: theBuf &rest)
		else
			((= theWin (systemWindow new:))
				color: color
				back: back
			)
			(if (and (not (HaveMouse)) (!= theCursor INVIS_CURSOR))
				(= saveCursor theCursor)
				(theGame setCursor: INVIS_CURSOR)
			else
				(= saveCursor 0)
			)
			(= printObj (Print new:))
			(if viewInPrint
				(= theLoop (if useFrame loop else (bust loop?)))
				(if showTitle (printObj addTitle: name))
				(printObj
					window: theWin
					dialog: (AnimDialog new:)
					posn: x y
					modeless: 0
					font: font
					addIcon: view theLoop cel 0 0
					addText: theBuf (+ x textX)
					width: theWidth
				)
			else
				(if (not (+ textX textY))
					(= textX (+ (- nsRight nsLeft) 5))
				)
				(if
				(> (+ (= temp2 (+ nsLeft textX)) talkWidth) 318)
					(= theWidth (- 318 temp2))
				else
					(= theWidth talkWidth)
				)
				(if showTitle (printObj addTitle: name))
				(printObj
					window: theWin
					dialog: (AnimDialog new:)
					posn: (+ x textX) (+ y textY)
					modeless: 0
					font: font
					addText: theBuf 4
					width: theWidth
				)
			)
			(if (not normal)
				(= temp5
					(+
						(-
							((= temp4 (NodeValue ((printObj dialog?) last:)))
								nsBottom?
							)
							(temp4 nsTop?)
						)
						4
					)
				)
				(= temp6 2)
				(while
					(= temp7
						(Message MsgSize modNum curNoun curVerb curCase temp6)
					)
					(printObj
						addColorButton: (- temp6 1) curNoun curVerb curCase temp6 4 temp5 modNum
					)
					(= temp4 (NodeValue ((printObj dialog?) last:)))
					(= temp5
						(+ temp5 (- (temp4 nsBottom?) (temp4 nsTop?)) 4)
					)
					(++ temp6)
				)
				(= whichSelect (printObj init:))
				(self dispose: 1)
			else
				(printObj width: theWidth init:)
			)
		)
	)
	
	(method (cycle obj &tmp theCel [str 100] port)
		(cond 
			(normal (super cycle: obj &rest))
			((and obj (obj cycler?))
				(= port (GetPort))
				(SetPort 0)
				(= theCel (obj cel?))
				((obj cycler?) doit:)
				(if (!= theCel (obj cel?))
					(DrawCel
						(obj view?)
						(obj loop?)
						(obj cel?)
						(+ (obj nsLeft?) nsLeft)
						(+ (obj nsTop?) nsTop)
						-1
					)
					(obj
						nsRight:
							(+
								(obj nsLeft?)
								(CelWide (obj view?) (obj loop?) (obj cel?))
							)
					)
					(obj
						nsBottom:
							(+
								(obj nsTop?)
								(CelHigh (obj view?) (obj loop?) (obj cel?))
							)
					)
					(Graph
						GShowBits
						(+ (obj nsTop?) nsTop)
						(+ (obj nsLeft?) nsLeft)
						(+ (obj nsBottom?) nsTop)
						(+ (obj nsRight?) nsLeft)
						1
					)
				)
				(SetPort port)
			)
		)
	)
)
