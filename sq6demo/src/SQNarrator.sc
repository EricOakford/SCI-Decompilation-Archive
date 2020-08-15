;;; Sierra Script 1.0 - (do not remove this comment)
(script# SQ6TALKERS)
(include game.sh)
(use Main)
(use String)
(use Print)
(use Talker)
(use RandCyc)
(use Motion)
(use Actor)


(class SQNarrator of Narrator
	(properties
		narratorBuf 0
		state 0
		narMod 0
		narNoun 0
		narVerb 0
		narCase 0
		narSeq 0
	)
	
	(method (say theBuf whoCares m n v c s)
		(= caller (if (and (> argc 1) whoCares) whoCares else 0))
		(if (& msgType TEXT_MSG)
			(= narratorBuf (String newWith: 400 (theBuf data?)))
		)
		(if (& msgType CD_MSG)
			(= narMod m)
			(= narNoun n)
			(= narVerb v)
			(= narCase c)
			(= narSeq s)
		)
		(if (not initialized)
			(self init:)
		else
			(self sayForReal:)
		)
	)
	
	(method (sayForReal)
		(if (& msgType (= state 1))
			(self startText: narratorBuf)
		)
		(if (& msgType CD_MSG)
			(self startAudio: narMod narNoun narVerb narCase narSeq)
		)
		(= ticks (+ ticks 60 gameTime))
	)
)

(class SmallTalker of SQNarrator
	(properties
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
						z: (client z?)
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
						z: (client z?)
						init:
						hide:
						yourself:
					)
				)
				(self cue:)
			)
			(else (self cue:))
		)
	)
	
	(method (dispose dWD)
		(if (or (not argc) dWD)
			(= state 2)
			(= scratch 1)
			(if prepMove
				(body view: bodyView setLoop: bodyLoop)
				(body setCel: (body lastCel:) setCycle: BegLoop self)
			else
				(self realDispose:)
			)
		else
			(= scratch 0)
			(self realDispose:)
		)
	)
	
	(method (startText &tmp theTicks)
		(= theTicks (super startText: &rest))
		(if (and (not (& msgType CD_MSG)) (or showTalk prepMove))
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
		(= audModNum m)
		(= audNoun n)
		(= audVerb v)
		(= audCase c)
		(super startAudio: m n v c (= audSequence s))
		(if (or showTalk prepMove)
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
		(return TRUE)
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
		(if scratch
			(= state 0)
			(if (or prepMove showTalk)
				(client show:)
				(body dispose:)
				(= body 0)
			)
		)
		(super dispose: scratch)
	)
)
