;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	MESSAGER.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1992
;;;;
;;;;	Author: 	Brian K. Hughes & Mark Wilden
;;;;	Updated:	Brian K. Hughes
;;;;
;;;;	This class controls the talkers in a game by invoking the appropriate
;;;;	one for each message.
;;;;
;;;;	Classes:
;;;;		Messager


(script# MESSAGER)
(include game.sh)
(use Main)
(use Print)
(use Game)
(use System)


(class Messager kindof Object
	(properties
		caller				NULL
		disposeWhenDone	TRUE	;should we get rid of all talkers after we're done?
		oneOnly				FALSE	;only display one message
		killed				FALSE	;if we were handed an ESC or right-click by talker
		oldIconBarState	NULL	;state of the iconbar upon entry
		curSequence			NULL	;current sequence number
		lastSequence		NULL	;high end of sequence range
	)

;;;	(methods
;;;		cue						;get a cue from the current talker
;;;		say						;startText or startAudio based on msgType
;;;		sayFormat				;say a formattable string (with parameters)
;;;		sayNext					;say the next message in a sequence, if any
;;;		findTalker				;find the object ID of the talker, given define
;;;	)

	(method (dispose)
		;
		; Get rid of us and all those silly talkers

		(talkerSet dispose:)
		(if theIconBar
			(theIconBar state: oldIconBarState)
			(= oldIconBarState 0)
		)
		(if caller
			(if (not cuees)
				(= cuees (Set new:))
			)
			(cuees add:
				((Cue new:)
					cuee:			caller,
					cuer:			self,
					register:	killed,
					yourself:
				)
			)
		)
		(super dispose:)
	)

	(method (cue killIt)
		;
		; When talker is done with message, find out if there's more

		(if (and argc killIt)
			(= killed TRUE)
		)
		(if (or oneOnly killed)
			(if fastCast
				(fastCast
					release:	,
					dispose:
				)
				(= fastCast 0)
			)
			(self dispose:)
		else
			(self sayNext:)
		)
	)

	(method (say args &tmp aNoun aVerb aCase aMod [buffer 20] i)
		;
		; Set up the parameters for saying a message & get a talker to
		;	say them.  Parameters take the following forms:
		;
		;		a) noun [verb [case [sequence [caller [module]]]]]
		;		b) noun [verb [case [fromSequence toSequence [caller [module]]]]]
		;		c) NEXT [caller]
		;
		; If two sequence numbers are specified (form b), they comprise a range.
		;	In this case, lastSequence will be set to the upper (last) sequence
		;	number.  If there is no range, this property will be NULL.

		(= aNoun (= aVerb (= aCase (= curSequence 0))))
		(= oneOnly (= killed FALSE))
		(= caller 0)
		(if (and	theIconBar
					(not oldIconBarState)
			)
			(= oldIconBarState (theIconBar state?))
		)

		; If the noun is NEXT (meaning next message in sequence), then check
		;	to see if the next parameter is a caller - in any case, skip the
		;	rest of the parameter parsing
		(= aNoun [args 0])
		(if (== aNoun NEXT)
		  	(if (and (> argc 1)
		  				(IsObject [args 1])
		  			)
				(= caller [args 1])
			)
			(self sayNext:)

		else

			; If we get a verb, use it
			(if (and (> argc 1) [args 1])
				(= aVerb [args 1])
			)

			; If we get a case, use it
			(if (and (> argc 2) [args 2])
				(= aCase [args 2])
			)

			; If we get a sequence number other than 0, then we want to say
			;	either that one message only or a range whose upper limit will be
			;	the next parameter.  A sequence number of 0 indicates that we
			;	want to say all sequences, so set it to 1.
			(if (and (> argc 3) [args 3])
				(= oneOnly TRUE)
				(= curSequence [args 3])
			else
				(= curSequence 1)
			)

			; If the next arg is not 0 and not an object, then it is the upper end
			;	of a sequence range.  In this case, be sure to set oneOnly FALSE.
			(= i 4)
			(if (and	(> argc i)
						[args i]
						(not (IsObject [args i]))
				)
				(= lastSequence [args i])
				(++ i)
				(= oneOnly FALSE)
			else
				(= lastSequence 0)
			)

			; See if we have a caller in parameter 4 (or 5 if sequence range)
			(if (and (> argc i) [args i])
				(= caller [args i])
			else
				(= caller 0)
			)

			; If there's a 5th (or 6th if sequence range) parameter, it'll be a
			;	module number.  If not, make the module the curRoomNum
			(= aMod
				(if (> argc (++ i))
					[args i]
				else
					curRoomNum
				)
			)

			; Now check to see if the message is out there, and if not display
			;	an error message
			(if (and	msgType
						(Message MsgGet aMod aNoun aVerb aCase curSequence)
				)
				(self sayNext: aMod aNoun aVerb aCase curSequence)
			else
				(Print
					addTextF:	{<Messager>\n
									\tmsgType set to 0 or\n
									\t%d: %d, %d, %d, %d not found}
										aMod aNoun aVerb aCase curSequence,
					init:
				)
				(self dispose:)
			)
		)
	)

	(method (sayFormat theTalker ctrlString args &tmp len buffer aTalker)
		;
		; Say a formattable string
		; Last arg is optional caller

		(= oneOnly (= killed FALSE))
		(= caller 0)
		(if (and	theIconBar
					(not oldIconBarState)
			)
			(= oldIconBarState (theIconBar state?))
		)

		(= aTalker (self findTalker: theTalker))
		(= len (FindFormatLen ctrlString args &rest))

		(if (IsObject [args (- argc 2)])
			(= caller [args (- argc 2)])
		)

		(= oneOnly TRUE)
		(= buffer (Memory MNeedPtr len))
		(Format buffer ctrlString args &rest)
		(aTalker say: buffer self)
		(Memory MDisposePtr buffer)
	)

	(method (sayNext theMod theNoun theVerb theCase theSeq
							&tmp aTalker [theBuf 200] msgkey)
		;
		; Find the talker and the message and introduce the two of them

		(if argc
			(= aTalker (Message MsgGet theMod theNoun theVerb theCase theSeq @theBuf))
		else
			(= aTalker (Message MsgNext @theBuf))
		)
		(if (& msgType CD_MSG)
			(= msgkey (Memory MNeedPtr 12))
			(Message MsgGetKey msgkey)
		)

		(if (and	aTalker
					(or	(not lastSequence)
							(and	lastSequence
									(<= curSequence lastSequence)
							)
					)
			)

			; Figure out who the talker object is
			(= aTalker (self findTalker: aTalker))

			(if (!= aTalker -1)
				; Add the talker to our list, and if the list doesn't exist create it
				(talkerSet add: aTalker)

				; Let the talker handle the message

				(if (& msgType CD_MSG)
					(aTalker
						modNum:	theMod,
						say:		msgkey self
					)
				else
					(aTalker
						modNum:	theMod,
						say:		@theBuf self theMod theNoun theVerb theCase theSeq
					)
				)

				(++ curSequence)
			else
				(if fastCast
					(fastCast
						release:	,
						dispose:
					)
					(= fastCast 0)
				)
				(self dispose:)
			)
	  	else
			(if fastCast
				(fastCast
					release:	,
					dispose:
				)
				(= fastCast 0)
			)
			(self dispose:)
		)
		(if (& msgType CD_MSG)
			(Memory MDisposePtr msgkey)
		)
	)

	(method (findTalker who)
		;
		; Find the object ID of a talker, given the define from TALKERS.SH
		; NOTE: This method must be over-ridden by your instance of Messager

		(Print
			width:		200,
			addText:		{<Messager findTalker:>\n
								\tCan't find talker or\n
								\tfindTalker method not over-ridden},
			init:
		)
		(return narrator)
	)
)


(instance talkerSet of Set
	(method (dispose)
		(self
			eachElementDo: #caller:		0,
			eachElementDo: #dispose: 	(messager disposeWhenDone?),
			release:
		)
		(super dispose:)
	)
)
