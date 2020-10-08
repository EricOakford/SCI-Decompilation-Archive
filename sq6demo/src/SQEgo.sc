;;; Sierra Script 1.0 - (do not remove this comment)
(script# SQ6EGO)
(include game.sh) (include "20.shm")
(use Main)
(use SQNarrator)
(use String)
(use Print)
(use RandCyc)
(use StopWalk)
(use Grooper)
(use Ego)
(use Motion)
(use Actor)

(public
	SQEgo 0
	Roger 1
)

(class SQEgo of Ego
	(properties
		name "ego"
		noun N_EGO
		modNum SQ6EGO
		view 900
		moveSpeed 4
	)
	
	(method (init)
		(super init:)
		(= signal (| signal (| skipCheck ignrHrz)))
		(if (not cycler)
			(self setCycle: Walk)
		)
		(Roger client: self)
	)
	
	(method (facingMe)
		(return TRUE)
	)
	
	(method (put what recipient &tmp obj i nextIcon)
		(if (self has: what)
			((= obj (inventory at: what))
				moveTo: (if (== argc 1) -1 else recipient)
			)
			(if
			(and theIconBar (== (theIconBar curInvIcon?) obj))
				(= i 0)
				(while (<= i NUM_INVITEMS)
					(if
						(and
							(not (& ((theIconBar at: i) signal?) DISABLED))
							(!= ((theIconBar at: i) message?) 5)
						)
						(= nextIcon (theIconBar at: i))
						(break)
					)
					(++ i)
				)
				(if
					(and
						(> i NUM_INVITEMS)
						(==
							((= nextIcon
								(theIconBar returnFirstAvail:)
							)
								message?
							)
							5
						)
					)
					(= nextIcon
						(theIconBar returnFirstAvail: 1)
					)
				)
				(theIconBar
					curInvIcon: 0
					disableIcon:
						((theIconBar useIconItem?)
							setCursor: normalCursor
							yourself:
						)
					clearInvItem:
					curIcon: nextIcon
				)
				(if (not (if (user controls?) (user input?)))
					(= oldCurIcon (theIconBar curIcon?))
				)
				(inventory selectedInvIcon: 0)
			)
		)
	)
	
	(method (normalize theView)
		(if (> argc 0)
			(ego view: theView)
		else
			(ego view: 900)
		)
		(ego
			setLoop: -1
			setLooper: GradualLooper
			loop: 0
			setPri: -1
			setMotion: 0
			setCycle: StopWalk (+ (self view?) 1)
			setStep: 3 2 0
			setSpeed: egoSpeed
			state: (= state (| state approachObj))
			ignoreActors: 0
		)
	)
)

(class EgoSmallTalker of SQNarrator
	(properties
		modeless DLG_MODELESS
		body 0
		bodyView 0
		bodyLoop 0
		bodyCel 0
		talkView 0
		talkLoop 0
		client 0
		stopWalkTalk TRUE
		prepMove 0
		showTalk TRUE
	)
	
	(method (init)
		(super init:)
		(= disposeWhenDone FALSE)
		(cond 
			(prepMove
				(= ticks -1)
				(= body
					((Prop new:)
						view: bodyView
						setLoop: bodyLoop
						setCel: bodyCel
						scaleSignal: (client scaleSignal?)
						scaleX: (client scaleX?)
						scaleY: (client scaleY?)
						x: (client x?)
						y: (client y?)
						init:
						setCycle: EndLoop self
						yourself:
					)
				)
				(client hide:)
			)
			(showTalk
				(= body
					((Prop new:)
						view: talkView
						setLoop: talkLoop
						setCel: bodyCel
						scaleSignal: (client scaleSignal?)
						scaleX: (client scaleX?)
						scaleY: (client scaleY?)
						x: (client x?)
						y: (client y?)
						init:
						hide:
						yourself:
					)
				)
				(self cue:)
			)
			(else
				(= body 0)
				(self cue:)
			)
		)
	)
	
	(method (dispose dWD)
		(if (or (not argc) dWD)
			(= state 2)
			(= scratch TRUE)
			(if prepMove
				(body view: bodyView setLoop: bodyLoop)
				(body setCel: (body lastCel:) setCycle: BegLoop self)
			else
				(self realDispose:)
			)
		else
			(= scratch FALSE)
			(self realDispose:)
		)
	)
	
	(method (startText &tmp theTicks)
		(= theTicks (super startText: &rest))
		(if (and (not (& msgType CD_MSG)) (or showTalk prepMove))
			(self setViews:)
			(body
				view: talkView
				setLoop: talkLoop
				setCel: bodyCel
				show:
				setCycle: RandCycle (* (/ theTicks 3) 2) 0 1
			)
			(client hide:)
		else
			(= ticks (* (/ theTicks 3) 2))
		)
	)
	
	(method (display theBuf &tmp theObj thePrint)
		((= thePrint (Print new:))
			x: 7
			y: 192
			font: userFont
			back: 0
			modeless: DLG_MODELESS
			init:
		)
		(if showTitle
			(= strHandle (String format: {%s\n} (self name?)))
			(showTitle addString: (strHandle data?) 50 84 0)
			(strHandle dispose:)
			(= strHandle (String format: {%s\n\n} theBuf))
			(= curText
				(showTitle addString: (strHandle data?) userFont 82 0 0)
			)
		)
		(prints delete: thePrint)
		(= dialog (thePrint dialog?))
	)
	
	(method (startAudio m n v c s)
		(super startAudio: m n v c s)
		(if (or showTalk prepMove)
			(self setViews:)
			(body
				view: talkView
				setLoop: talkLoop
				setCel: bodyCel
				show:
				setCycle: RandCycle
			)
			(client hide:)
		)
	)
	
	(method (canTalk)
		(return FALSE)
	)
	
	(method (cue)
		(cond 
			((== state 0)
				(self sayForReal:)
			)
			((== state 2)
				(self realDispose:)
			)
		)
	)
	
	(method (setViews)
		(= prepMove TRUE)
		(switch (ego loop?)
			(loopE
				(switch targetAngles
					(dirStop
						(= bodyView 940)
						(= bodyLoop 0)
						(= talkView 9401)
						(= talkLoop 0)
					)
					(dirN
						(= bodyView 940)
						(= bodyLoop 0)
						(= talkView 9400)
						(= talkLoop 0)
					)
					(dirNE
						(= bodyView 940)
						(= bodyLoop 2)
						(= talkView 9400)
						(= talkLoop 2)
					)
					(dirE
						(= bodyView 940)
						(= bodyLoop 2)
						(= talkView 9401)
						(= talkLoop 2)
					)
				)
			)
			(loopW
				(switch targetAngles
					(dirStop
						(= bodyView 940)
						(= bodyLoop 1)
						(= talkView 9401)
						(= talkLoop 1)
					)
					(dirN
						(= bodyView 940)
						(= bodyLoop 1)
						(= talkView 9400)
						(= talkLoop 1)
					)
					(dirNE
						(= bodyView 940)
						(= bodyLoop 3)
						(= talkView 9400)
						(= talkLoop 3)
					)
					(dirE
						(= bodyView 940)
						(= bodyLoop 3)
						(= talkView 9401)
						(= talkLoop 3)
					)
				)
			)
			(loopS
				(switch targetAngles
					(dirStop
						(= bodyView 942)
						(= bodyLoop 0)
						(= talkView 9421)
						(= talkLoop 0)
					)
					(dirN
						(= bodyView 942)
						(= bodyLoop 0)
						(= talkView 9420)
						(= talkLoop 0)
					)
					(dirNE
						(= bodyView 942)
						(= bodyLoop 2)
						(= talkView 9420)
						(= talkLoop 2)
					)
					(dirE
						(= bodyView 942)
						(= bodyLoop 2)
						(= talkView 9421)
						(= talkLoop 2)
					)
				)
			)
			(loopN
				(if (== targetAngles dirN)
					(= bodyView 942)
					(= bodyLoop 1)
					(= talkView 9420)
					(= talkLoop 1)
				else
					(= prepMove 0)
					(= talkView 9421)
					(= talkLoop 1)
				)
			)
			(loopSE
				(switch targetAngles
					(dirStop
						(= bodyView 944)
						(= bodyLoop 0)
						(= talkView 9441)
						(= talkLoop 0)
					)
					(dirN
						(= bodyView 944)
						(= bodyLoop 0)
						(= talkView 9440)
						(= talkLoop 0)
					)
					(dirNE
						(= bodyView 944)
						(= bodyLoop 2)
						(= talkView 9440)
						(= talkLoop 2)
					)
					(dirE
						(= bodyView 944)
						(= bodyLoop 2)
						(= talkView 9441)
						(= talkLoop 2)
					)
				)
			)
			(loopSW
				(switch targetAngles
					(dirStop
						(= bodyView 944)
						(= bodyLoop 1)
						(= talkView 9441)
						(= talkLoop 1)
					)
					(dirN
						(= bodyView 944)
						(= bodyLoop 1)
						(= talkView 9440)
						(= talkLoop 1)
					)
					(dirNE
						(= bodyView 944)
						(= bodyLoop 3)
						(= talkView 9440)
						(= talkLoop 3)
					)
					(dirE
						(= bodyView 944)
						(= bodyLoop 3)
						(= talkView 9441)
						(= talkLoop 3)
					)
				)
			)
			(loopNE
				(if (== targetAngles dirNE)
					(= bodyView 946)
					(= bodyLoop 0)
					(= talkView 9460)
					(= talkLoop 0)
				else
					(= prepMove 0)
					(= talkView 9461)
					(= talkLoop 0)
				)
			)
			(loopNW
				(if (== targetAngles dirN)
					(= bodyView 946)
					(= bodyLoop 1)
					(= talkView 9460)
					(= talkLoop 1)
				else
					(= prepMove 0)
					(= talkView 9461)
					(= talkLoop 1)
				)
			)
		)
	)
	
	(method (realDispose)
		(if (and (& msgType TEXT_MSG) narratorBuf)
			(narratorBuf dispose:)
			(= narratorBuf 0)
		)
		(if curText
			(showTitle addString: {} 50 84 0)
			(showTitle addString: {} userFont 82 0)
			(strHandle dispose:)
			(= curText 0)
		)
		(cond 
			(scratch
				(= state 0)
				(if (or prepMove showTalk)
					(client show:)
					(body dispose:)
					(= body 0)
				)
			)
			(body (body setCycle: 0 setCel: 0))
		)
		(super dispose: scratch)
	)
)

(instance Roger of EgoSmallTalker
	
	(method (init)
		(if (and (cast contains: ego) (self canTalk:))
			(self setViews:)
		else
			(= showTalk (= prepMove 0))
		)
		(super init:)
	)
	
	(method (canTalk)
		(return
			(if (== (ego view?) 901)
				(not (& (inventory state?) IB_ACTIVE))
			else
				FALSE
			)
		)
	)
)
